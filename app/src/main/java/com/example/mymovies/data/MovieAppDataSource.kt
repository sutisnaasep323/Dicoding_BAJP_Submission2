package com.example.mymovies.data

import androidx.lifecycle.LiveData
import com.example.mymovies.data.source.local.MovieEntity
import com.example.mymovies.data.source.local.TvShowEntity

interface MovieAppDataSource {

    fun getAllTvShows(): LiveData<List<TvShowEntity>>

    fun getTvShowById(tvShowId: String): LiveData<TvShowEntity>

    fun getAllMovies(): LiveData<List<MovieEntity>>

    fun getMovieById(movieId: String): LiveData<MovieEntity>

}