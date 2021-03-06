package com.example.movies.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (

    val adult : Boolean,
    val backdrop_path : String,
    val genre_ids : List<Int>,
    val id : Int,
    val original_language : String,
    val original_title : String,
    val overview : String,
    val popularity : Double,
    val poster_path : String,
    val release_date : String,
    var title : String = "https://image.tmdb.org/t/p/original",
    val video : Boolean,
    val vote_average : Double,
    val vote_count : Int
) : Parcelable
