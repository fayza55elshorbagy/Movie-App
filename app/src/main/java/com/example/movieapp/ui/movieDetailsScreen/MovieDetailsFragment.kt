package com.example.movieapp.ui.movieDetailsScreen

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.dataLayer.RepositoryImpl
import com.example.movieapp.dataLayer.network.RemoteDataSourceImpl
import com.example.movieapp.dataLayer.room.RoomDataSourceImpl
import com.example.movieapp.databinding.FragmentMovieDetailsBinding
import com.example.movieapp.ui.base.ViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDetailsFragment : Fragment() {

    private var disposable: CompositeDisposable = CompositeDisposable()
    lateinit var progressDialog : ProgressDialog
    lateinit var movieDetailsScreenViewModel: MovieDetailsViewModel
    lateinit var binding : FragmentMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressDialog = ProgressDialog(activity)
        progressDialog.setTitle("Please wait...")
        progressDialog.show()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailsBinding.inflate(inflater,container,false)
        val application = requireActivity().application
        val repository = RepositoryImpl(RemoteDataSourceImpl(), RoomDataSourceImpl(requireContext()))

        val viewModelFactory = ViewModelFactory(repository,application)
        movieDetailsScreenViewModel = ViewModelProvider(this,viewModelFactory).get(MovieDetailsViewModel::class.java)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args: MovieDetailsFragmentArgs by navArgs()
        disposable.add(movieDetailsScreenViewModel.getMovieDetails(args.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            movieDetails ->
                            progressDialog.dismiss()
                            Glide.with(requireContext())
                                .load( movieDetails.image )
                                .into(binding.posterImageView!!)
                            binding.desciptionD!!.text = movieDetails.genres
                            binding.movieName!!.text = movieDetails.title
                            binding.rateD!!.text = movieDetails.imDbRating

                            val layoutManager1 =
                                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                            val layoutManager2 =
                                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                            binding.actorList.layoutManager = layoutManager1
                            binding.directorList.layoutManager = layoutManager2

                            binding.actorList.adapter = ActorAdapter(requireContext(),movieDetails.actorList)
                            binding.directorList.adapter = OptionsAdapter(movieDetails.directorList )
                            binding.addTofav!!.setOnClickListener {
                                movieDetailsScreenViewModel.saveFavMovie(movieDetails)
                                Toast.makeText(requireContext(),"Added",Toast.LENGTH_LONG).show()
                            }


                        },
                        { error ->
                            progressDialog.dismiss()
                        }
                ))

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}