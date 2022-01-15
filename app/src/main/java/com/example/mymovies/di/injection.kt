package com.example.mymovies.di

import com.example.mymovies.data.MovieAppRepository
import com.example.mymovies.data.source.remote.RemoteDataSource

object Injection {

    fun provideRepository(): MovieAppRepository {
        val remoteRepository = RemoteDataSource.getInstance()
        return MovieAppRepository.getInstance(remoteRepository)
    }
}