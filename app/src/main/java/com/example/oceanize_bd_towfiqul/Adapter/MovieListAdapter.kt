package com.example.oceanize_bd_towfiqul.Adapter

import android.view.ViewGroup

import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

import com.example.oceanize_bd_towfiqul.Database.Model.MovieListData


class MovieListAdapter : PagedListAdapter<MovieListData, MovieListViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder =
        MovieListViewHolder(parent)

    companion object {
        /**
         * This diff callback informs the PagedListAdapter how to compute list differences when new
         * PagedLists arrive.
         * <p>
         * When you add a MovieListData with the 'Add' button, the PagedListAdapter uses diffCallback to
         * detect there's only a single item difference from before, so it only needs to animate and
         * rebind a single view.
         *
         * @see DiffUtil
         */
        private val diffCallback = object : DiffUtil.ItemCallback<MovieListData>() {
            override fun areItemsTheSame(oldItem: MovieListData, newItem: MovieListData): Boolean =
                oldItem.id == newItem.id

            /**
             * Note that in kotlin, == checking on data classes compares all contents, but in Java,
             * typically you'll implement Object#equals, and use it to compare object contents.
             */
            override fun areContentsTheSame(oldItem: MovieListData, newItem: MovieListData): Boolean =
                oldItem == newItem
        }
    }
}