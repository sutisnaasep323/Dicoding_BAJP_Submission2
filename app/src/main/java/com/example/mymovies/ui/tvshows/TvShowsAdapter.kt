package com.example.mymovies.ui.tvshows

import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.mymovies.data.source.local.TvShowEntity
import com.example.mymovies.databinding.ItemsListBinding
import com.example.mymovies.ui.detail.DetailMoviesActivity
import com.example.mymovies.ui.detail.DetailType

class TvShowsAdapter : RecyclerView.Adapter<TvShowsAdapter.TvShowsViewHolder>() {

    private var listMovies = ArrayList<TvShowEntity>()

    fun setTvShows(tvShow: List<TvShowEntity>?) {
        if (tvShow == null) return
        this.listMovies.clear()
        this.listMovies.addAll(tvShow)
    }

    class TvShowsViewHolder(private val binding: ItemsListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity) {
            with(binding) {
                title.text = tvShow.name
                date.text = tvShow.firstAirDate
                popularity.text = tvShow.popularity.toString()
                language.text = tvShow.originalLanguage
                sinopsis.text = tvShow.overview
                userScore.text = tvShow.voteAverage.toString()
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMoviesActivity::class.java)
                    intent.putExtra(DetailMoviesActivity.EXTRA_TYPE, DetailType.TV_SHOWS.ordinal)
                    intent.putExtra(DetailMoviesActivity.EXTRA_ID, tvShow.id)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/original/" + tvShow.posterPath)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressBar.visibility = View.GONE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressBar.visibility = View.GONE
                            return false
                        }
                    })
                    .into(posterDetail)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsViewHolder {
        val itemsAcademyBinding =
            ItemsListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return TvShowsViewHolder(itemsAcademyBinding)

    }

    override fun onBindViewHolder(holder: TvShowsViewHolder, position: Int) {
        val tvShow = listMovies[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listMovies.size

}