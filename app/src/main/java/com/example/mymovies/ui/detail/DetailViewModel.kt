package com.example.mymovies.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mymovies.data.MovieAppRepository
import com.example.mymovies.data.source.local.MovieEntity
import com.example.mymovies.data.source.local.TvShowEntity
import com.example.mymovies.utils.DataDummy

class DetailViewModel(private val movieAppRepository: MovieAppRepository) : ViewModel() {

    private lateinit var movieId: String
    private lateinit var tvShowId: String

    fun selectedMovieId(movieId: String) {
        this.movieId = movieId
    }

    fun selectedTvShowId(tvShowId: String) {
        this.tvShowId = tvShowId
    }

    fun getMovieDetail(): LiveData<MovieEntity> =
        movieAppRepository.getMovieById(movieId)

    fun getTvShowDetail(): LiveData<TvShowEntity> =
        movieAppRepository.getTvShowById(tvShowId)

}