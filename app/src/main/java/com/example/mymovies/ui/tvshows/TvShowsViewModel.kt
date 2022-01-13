package com.example.mymovies.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mymovies.data.MovieAppRepository
import com.example.mymovies.data.source.local.TvShowEntity
import com.example.mymovies.utils.DataDummy

class TvShowsViewModel(private val movieAppRepository: MovieAppRepository) : ViewModel() {

    fun getTvShows(): LiveData<List<TvShowEntity>> = movieAppRepository.getAllTvShows()

}