package com.example.movies.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.network.MoviesApi
import com.example.movies.models.Data
import com.example.movies.models.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

import retrofit2.await

enum class MovieApiStatus { LOADING, ERROR, DONE }


class MoviesFragmentViewModel : ViewModel() {
    private val _status = MutableLiveData<MovieApiStatus>()
    val status : LiveData<MovieApiStatus>
        get() = _status

    private val _movies = MutableLiveData<List<Movie>>()
    val movies : LiveData<List<Movie>>
        get() = _movies


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
        getMovies()
    }


    private fun getMovies(){
        coroutineScope.launch {
            val getPropertiesDeferred = MoviesApi.retrofitService.getMovies()
            try {
                _status.value = MovieApiStatus.LOADING
                val response: Data = getPropertiesDeferred.await()
                _status.value = MovieApiStatus.DONE
                val results : List<Movie> = response.results
                _movies.value = results
            } catch (e: Exception) {
                _status.value = MovieApiStatus.ERROR
                _movies.value = ArrayList()
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}