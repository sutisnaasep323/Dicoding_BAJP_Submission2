package com.example.mymovies.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mymovies.data.MovieAppRepository
import com.example.mymovies.data.source.local.MovieEntity
import com.example.mymovies.utils.DataDummy

class MoviesViewModel(private val movieAppRepository: MovieAppRepository) : ViewModel() {

    fun getMovies(): LiveData<List<MovieEntity>> = movieAppRepository.getAllMovies()

}