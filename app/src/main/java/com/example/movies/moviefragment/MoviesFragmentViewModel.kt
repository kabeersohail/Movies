package com.example.movies.moviefragment

import android.util.Log
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
    private var totalData = ArrayList<Movie>()
    var pageNumber = 1
    private val _status = MutableLiveData<MovieApiStatus>()
    val status : LiveData<MovieApiStatus>
        get() = _status

    private val _movies = MutableLiveData<MutableList<Movie>>()
    val movies : LiveData<MutableList<Movie>>
        get() = _movies

    private val _navigateToSelectedProperty = MutableLiveData<Movie>()

    val navigateToSelectedProperty: LiveData<Movie>
        get() = _navigateToSelectedProperty


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
        getMovies1()
    }


    fun getMovies1(){
        pageNumber += 1

        coroutineScope.launch {
            val getPropertiesDeferred = MoviesApi.retrofitService.getMovies(pageNumber)
            try {
                val response: Data = getPropertiesDeferred.await()
                val results : MutableList<Movie> = response.results as MutableList<Movie>
                if(_movies.value == null){
                    _movies.value = (results)
                    Log.d("TAG","Added")
                }
                else{
                    _movies.value?.addAll(results)
                    Log.d("TAG","New data $pageNumber")

                }
            } catch (e: Exception) {
                _status.value = MovieApiStatus.ERROR
//                _movies.value = ArrayList()
            }
        }
    }

    fun displayPropertyDetails(movie: Movie) {
        _navigateToSelectedProperty.value = movie
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }



}