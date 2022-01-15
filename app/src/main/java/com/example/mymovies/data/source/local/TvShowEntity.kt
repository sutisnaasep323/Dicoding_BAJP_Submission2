package com.example.mymovies.data.source.local

data class TvShowEntity(
    val firstAirDate: String,
    val overview: String,
    val originalLanguage: String,
    val popularity: Double,
    val voteAverage: Double,
    val name: String,
    val id: Int,
    val posterPath: String,
)