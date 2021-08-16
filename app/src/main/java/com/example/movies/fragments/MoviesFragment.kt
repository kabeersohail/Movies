package com.example.movies.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.movies.adapters.MovieGridAdapter
import com.example.movies.databinding.FragmentProductsBinding

class MoviesFragment : Fragment() {

    private val viewModel : MoviesFragmentViewModel by lazy {
        ViewModelProvider(this).get(MoviesFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val binding: TestBinding = DataBindingUtil.inflate(inflater,R.layout.test,container,false)
        val binding = FragmentProductsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.photosGrid.adapter = MovieGridAdapter()
        return binding.root
    }

}