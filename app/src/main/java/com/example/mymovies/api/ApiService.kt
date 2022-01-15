package com.example.mymovies.api

import com.example.mymovies.data.source.remote.response.MoviesResponse
import com.example.mymovies.data.source.remote.response.TvShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getMovies(
        @Query("api_key") apiKey: String = "KEY"
    ): Call<MoviesResponse>

    @GET("tv/popular")
    fun getTvShows(
        @Query("api_key") apiKey: String = "KEY"
    ): Call<TvShowsResponse>
}