package com.example.movieapp.ui.homeScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.dataLayer.RepositoryImpl
import com.example.movieapp.dataLayer.entity.MovieDetails
import com.example.movieapp.dataLayer.network.RemoteDataSourceImpl
import com.example.movieapp.dataLayer.room.RoomDataSourceImpl
import com.example.movieapp.databinding.FragmentHomeScreenBinding
import com.example.movieapp.ui.base.ItemsRecyclerClick
import com.example.movieapp.ui.base.NetworkChangeReceiver
import com.example.movieapp.ui.base.ViewModelFactory
import com.example.movieapp.ui.movieDetailsScreen.ActorAdapter
import com.example.movieapp.ui.movieDetailsScreen.MovieDetailsViewModel
import com.example.movieapp.ui.searchScreen.SearchFragmentDirections
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeScreenFragment : Fragment(),ItemsRecyclerClick {


    lateinit var homeViewModel: HomeViewModel
    lateinit var binding : FragmentHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeScreenBinding.inflate(inflater,container,false)
        val application = requireActivity().application
        val repository = RepositoryImpl(RemoteDataSourceImpl(), RoomDataSourceImpl(requireContext()))

        val viewModelFactory = ViewModelFactory(repository,application)
        homeViewModel = ViewModelProvider(this,viewModelFactory).get(
            HomeViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(NetworkChangeReceiver.isOnline){
            binding.emptyPage.visibility = View.VISIBLE
            binding.homeCard.visibility = View.VISIBLE
            binding.networkView.visibility = View.GONE
            homeViewModel.getAllFav().observe(viewLifecycleOwner, Observer {
                if(it.isNotEmpty()){
                    binding.emptyPage.visibility = View.GONE
                    binding.favItemRecView.visibility = View.VISIBLE
                    binding.favItemRecView.adapter = HomeAdapter(requireContext(),it,this@HomeScreenFragment)
                }
            })

        }else{
            binding.homeCard.visibility = View.GONE
            binding.networkView.visibility = View.VISIBLE
            binding.emptyPage.visibility = View.GONE

        }


    }

    override fun itemOnClick(itemId: String) {
        val action = HomeScreenFragmentDirections.toMovieDetails(itemId)
        findNavController().navigate(action)
    }

    override fun deleteMovie(itemId: String) {

    }

}