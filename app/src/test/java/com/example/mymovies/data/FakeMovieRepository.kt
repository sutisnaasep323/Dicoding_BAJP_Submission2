package com.example.mymovies.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymovies.data.source.local.MovieEntity
import com.example.mymovies.data.source.local.TvShowEntity
import com.example.mymovies.data.source.remote.RemoteDataSource
import com.example.mymovies.data.source.remote.response.Movie
import com.example.mymovies.data.source.remote.response.TvShow
import com.example.mymovies.utils.DataDummy
import com.example.mymovies.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class FakeMovieRepository (private val remoteDataSource: RemoteDataSource): MovieAppDataSource {

    override fun getAllMovies(): LiveData<List<MovieEntity>> {
        val movieResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponses: List<Movie>?) {
                val movieList = ArrayList<MovieEntity>()
                if (movieResponses != null) {
                    for (movie in movieResponses) {
                        with(movie) {
                            movieList.add(
                                MovieEntity(
                                    overview,
                                    originalLanguage,
                                    releaseDate,
                                    popularity,
                                    voteAverage,
                                    id,
                                    title,
                                    posterPath
                                )
                            )
                        }
                    }
                }
                movieResults.postValue(movieList)
            }
        })
        return movieResults
    }

    override fun getAllTvShows(): LiveData<List<TvShowEntity>> {
        val tvShowResults = MutableLiveData<List<TvShowEntity>>()
        remoteDataSource.getTvShows(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onAllTvShowsReceived(tvShowResponses: List<TvShow>?) {
                val tvShowList = ArrayList<TvShowEntity>()
                if (tvShowResponses != null) {
                    for (tvShow in tvShowResponses) {
                        with(tvShow) {
                            tvShowList.add(
                                TvShowEntity(
                                    firstAirDate,
                                    overview,
                                    originalLanguage,
                                    popularity,
                                    voteAverage,
                                    name,
                                    id,
                                    posterPath,
                                )
                            )
                        }
                    }
                }
                tvShowResults.postValue(tvShowList)
            }
        })
        return tvShowResults
    }

    override fun getMovieById(movieId: String): LiveData<MovieEntity> {
        val movieResult = MutableLiveData<MovieEntity>()
        remoteDataSource.getMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponses: List<Movie>?) {
                lateinit var movie: MovieEntity
                if (movieResponses != null) {
                    for (movieItem in movieResponses) {
                        if (movieId == movieItem.id.toString())
                            with(movieItem) {
                                movie = MovieEntity(
                                    overview,
                                    originalLanguage,
                                    releaseDate,
                                    popularity,
                                    voteAverage,
                                    id,
                                    title,
                                    posterPath
                                )
                            }
                    }
                }
                movieResult.postValue(movie)
            }
        })
        return movieResult
    }

    override fun getTvShowById(tvShowId: String): LiveData<TvShowEntity> {
        val tvShowResult = MutableLiveData<TvShowEntity>()
        remoteDataSource.getTvShows(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onAllTvShowsReceived(tvShowResponses: List<TvShow>?) {
                lateinit var tvShow: TvShowEntity
                if (tvShowResponses != null) {
                    for (tvShowItem in tvShowResponses) {
                        if (tvShowId == tvShowItem.id.toString())
                            with(tvShowItem) {
                                tvShow = TvShowEntity(
                                    firstAirDate,
                                    overview,
                                    originalLanguage,
                                    popularity,
                                    voteAverage,
                                    name,
                                    id,
                                    posterPath,
                                )
                            }
                    }
                }
                tvShowResult.postValue(tvShow)
            }
        })
        return tvShowResult
    }
}