package com.example.mymovies.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mymovies.R
import com.example.mymovies.data.source.local.MovieEntity
import com.example.mymovies.data.source.local.TvShowEntity
import com.example.mymovies.databinding.ActivityDetailMoviesBinding
import com.example.mymovies.viewmodel.ViewModelFactory

class DetailMoviesActivity : AppCompatActivity() {

    private lateinit var detailMoviesBinding: ActivityDetailMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailMoviesBinding = ActivityDetailMoviesBinding.inflate(layoutInflater)

        setContentView(detailMoviesBinding.root)

        detailMoviesBinding.backButton.setOnClickListener { onBackPressed() }

        window.statusBarColor = ContextCompat.getColor(this, R.color.purple_700)
        val type = intent.getIntExtra(EXTRA_TYPE, -1)
        val enumType: DetailType = DetailType.values()[type]
        val id = intent.getIntExtra(EXTRA_ID, -1)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        detailMoviesBinding.progressBar.visibility = View.VISIBLE
        detailMoviesBinding.nestedScrollView.visibility = View.GONE
        detailMoviesBinding.collapsingToolbar.visibility = View.GONE

        when (enumType) {
            DetailType.MOVIES -> {
                viewModel.selectedMovieId(id.toString())
                viewModel.getMovieDetail().observe(this, { movie ->
                    detailMoviesBinding.progressBar.visibility = View.GONE
                    detailMoviesBinding.nestedScrollView.visibility = View.VISIBLE
                    detailMoviesBinding.collapsingToolbar.visibility = View.VISIBLE
                    populateMovieDetail(movie)
                })
            }
            DetailType.TV_SHOWS -> {
                viewModel.selectedTvShowId(id.toString())
                viewModel.getTvShowDetail().observe(this, { tvShow ->
                    detailMoviesBinding.progressBar.visibility = View.GONE
                    detailMoviesBinding.nestedScrollView.visibility = View.VISIBLE
                    detailMoviesBinding.collapsingToolbar.visibility = View.VISIBLE
                    populateTvShowsDetail(tvShow)
                })
            }
        }

    }

    private fun populateMovieDetail(movie: MovieEntity) {
        with(detailMoviesBinding) {
            titleDetail.text = movie.title
            date.text = movie.releaseDate
            sinopisDetail.text = movie.overview
            popularityDetail.text = movie.popularity.toString()
            scoreDetail.text = movie.voteAverage.toString()
            languageDetail.text = movie.originalLanguage

            Glide.with(this@DetailMoviesActivity)
                .load("https://image.tmdb.org/t/p/w500/" + movie.posterPath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
                .into(detailMoviesBinding.posterDetail)
        }
    }

    private fun populateTvShowsDetail(tvShow: TvShowEntity) {
        with(detailMoviesBinding) {
            titleDetail.text = tvShow.name
            date.text = tvShow.firstAirDate
            sinopisDetail.text = tvShow.overview
            popularityDetail.text = tvShow.popularity.toString()
            scoreDetail.text = tvShow.voteAverage.toString()
            languageDetail.text = tvShow.originalLanguage

            Glide.with(this@DetailMoviesActivity)
                .load("https://image.tmdb.org/t/p/original/" + tvShow.posterPath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
                .into(detailMoviesBinding.posterDetail)
        }
    }

    companion object {
        const val EXTRA_TYPE = "extraType"
        const val EXTRA_ID = "extraId"
    }
}

