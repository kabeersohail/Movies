package com.example.movies.models


data class Data (

    val page : Int,
    val results : List<Movie>,
    val total_pages : Int,
    val total_results : Int
)
