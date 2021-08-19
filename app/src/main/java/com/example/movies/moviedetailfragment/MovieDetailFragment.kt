package com.example.movies.moviedetailfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.movies.R
import com.example.movies.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentMovieDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val movie = MovieDetailFragmentArgs.fromBundle(requireArguments()).selectedProperty
        val viewModelFactory = MovieDetailViewModelFactory(movie, application)
        binding.viewModel = ViewModelProvider(
            this, viewModelFactory).get(MovieDetailViewModel::class.java)
        return binding.root
    }
}