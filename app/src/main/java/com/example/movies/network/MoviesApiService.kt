package com.example.movies.network

import com.example.movies.models.Data
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org/3/"

private val BASE_URL_IMAGES = "https://image.tmdb.org/t/p/original/iCi4c4FvVdbaU1t8poH1gvzT6xM.jpg"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface MoviesApiService{
    @GET("movie/popular?api_key=cbd4b92662bd89da04fdd8e3e2fef7cb&language=en-US&page=3")
    fun getMovies() : Call<Data>
}

object MoviesApi{
    val retrofitService : MoviesApiService by lazy {
        retrofit.create(MoviesApiService::class.java)
    }
}