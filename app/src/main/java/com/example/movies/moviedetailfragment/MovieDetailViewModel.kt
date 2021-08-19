package com.example.movies.moviedetailfragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movies.models.Movie

class MovieDetailViewModel(movie: Movie,app:Application) : AndroidViewModel(app) {
    private val _selectedProperty = MutableLiveData<Movie>()

    val selectedProperty: LiveData<Movie>
        get() = _selectedProperty

    init {
        _selectedProperty.value = movie
    }
}