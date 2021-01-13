package com.example.oceanize_bd_towfiqul.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.oceanize_bd_towfiqul.Database.Model.MovieListData
import com.example.oceanize_bd_towfiqul.R
import com.example.oceanize_bd_towfiqul.Util.Constants

class MovieListViewHolder (private val parent : ViewGroup) : RecyclerView.ViewHolder(
LayoutInflater.from(parent.context).inflate(R.layout.movielist_row_item, parent, false)) {
    private val movieTitle = itemView.findViewById<TextView>(R.id.tv_movie_title)
    private val moviePoster = itemView.findViewById<ImageView>(R.id.imv_movie_poster)
    var movieListData : MovieListData? = null

    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bindTo(movieListData : MovieListData?) {
        this.movieListData = movieListData
        if (movieListData != null) {
            movieTitle.typeface = ResourcesCompat.getFont(parent.context, R.font.monotype)
            movieTitle.text = movieListData.title
        }
        if (movieListData != null) {
            Glide.with(parent.context)
                .load(Constants.IMAGE_URL+ movieListData.posterPath)
                .centerInside()
                .placeholder(R.drawable.moviel_list_place_holer)
                .into(moviePoster)
        };
    }
}
