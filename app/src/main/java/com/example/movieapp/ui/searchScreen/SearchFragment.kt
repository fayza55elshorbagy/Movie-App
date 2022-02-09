package com.example.movieapp.ui.searchScreen

import android.app.ProgressDialog
import android.graphics.Color
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.dataLayer.RepositoryImpl
import com.example.movieapp.dataLayer.entity.HiddenMovies
import com.example.movieapp.dataLayer.entity.Results
import com.example.movieapp.dataLayer.entity.SearchedMovie
import com.example.movieapp.dataLayer.network.RemoteDataSourceImpl
import com.example.movieapp.dataLayer.room.RoomDataSourceImpl
import com.example.movieapp.databinding.SearchFragmentBinding
import com.example.movieapp.ui.base.ItemsRecyclerClick
import com.example.movieapp.ui.base.NetworkChangeReceiver
import com.example.movieapp.ui.base.ViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*

class SearchFragment : Fragment(), ItemsRecyclerClick {

    private var disposable: CompositeDisposable = CompositeDisposable()
    lateinit var mainScreenViewModel: SearchedMovieViewModel
    lateinit var progressDialog: ProgressDialog
    lateinit var moviesFromApi: SearchedMovie
    var hiddenList: List<HiddenMovies>? = null
    lateinit var binding: SearchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchFragmentBinding.inflate(inflater, container, false)
        val application = requireActivity().application
        val repository =
            RepositoryImpl(RemoteDataSourceImpl(), RoomDataSourceImpl(requireContext()))

        val viewModelFactory = ViewModelFactory(repository, application)
        mainScreenViewModel =
            ViewModelProvider(this, viewModelFactory).get(SearchedMovieViewModel::class.java)

        progressDialog = ProgressDialog(activity)

        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (NetworkChangeReceiver.isOnline) {
            binding.searchCard.visibility = View.VISIBLE
            binding.networkView.visibility = View.GONE
        } else {
            binding.searchCard.visibility = View.GONE
            binding.networkView.visibility = View.VISIBLE
        }


        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                progressDialog.setTitle("Please wait...")
                progressDialog.show()
                submitQuery(query!!)

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

    }

    fun submitQuery(query: String) {
        CoroutineScope(Dispatchers.Main).launch {

            runBlocking {
                val networkCall = mainScreenViewModel.getSearchedMovie(query)
                if(networkCall.isSuccessful)
                    moviesFromApi = networkCall.body()!!
                else Toast.makeText(requireContext(),networkCall.errorBody().toString(),Toast.LENGTH_LONG).show()
            }
            runBlocking {
                mainScreenViewModel.getHiddenMovies().observe(viewLifecycleOwner, Observer {
                    hiddenList = it
                    var mutableList = moviesFromApi.results.toMutableList()
                    for (i in hiddenList!!) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            mutableList.removeIf { it.id == i.movieDetails.id }
                        }
                    }
                    progressDialog.dismiss()
                    val layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    binding.recyclerView.layoutManager = layoutManager
                    binding.recyclerView.adapter =
                        SearchedMovieAdapter(
                            requireContext(),
                            mutableList, this@SearchFragment
                        )
                })

            }

        }
    }


    override fun itemOnClick(item: String) {
        val action = SearchFragmentDirections.toMovieDetails(item)
        findNavController().navigate(action)

    }

    override fun deleteMovie(itemId: String) {
        showAlert(itemId)
    }

    private fun showAlert(itemId: String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Alert")
        builder.setMessage("Are you sure you want to hide this movie/show from future search?")

        builder.setPositiveButton("Yes") { _, _ ->
            mainScreenViewModel.getMovieDetails(itemId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { movie ->
                        mainScreenViewModel.saveHiddenMovie(HiddenMovies(0, movie))

                    },
                    { error ->
                    }
                )
        }

        builder.setNegativeButton("Cancel") { _, _ ->
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setBackgroundColor(Color.BLACK)
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.WHITE)
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.DKGRAY)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }

}