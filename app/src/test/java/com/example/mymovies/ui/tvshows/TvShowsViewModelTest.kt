package com.example.mytvShow.ui.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.mymovies.data.MovieAppRepository
import com.example.mymovies.data.source.local.TvShowEntity
import com.example.mymovies.ui.tvshows.TvShowsViewModel
import com.example.mymovies.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowsViewModelTest{
    private lateinit var viewModel: TvShowsViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieAppRepository: MovieAppRepository

    @Mock
    private lateinit var observer: Observer<List<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = TvShowsViewModel(movieAppRepository)
    }

    @Test
    fun getMovies() {
        val dummyTvShows = DataDummy.generateDummyTvShows()
        val tvShow = MutableLiveData<List<TvShowEntity>>()
        tvShow.value = dummyTvShows

        Mockito.`when`(movieAppRepository.getAllTvShows()).thenReturn(tvShow)
        val tvShowsEntities = viewModel.getTvShows().value
        verify(movieAppRepository).getAllTvShows()
        assertNotNull(tvShowsEntities)
        assertEquals(3, tvShowsEntities?.size)

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }
}