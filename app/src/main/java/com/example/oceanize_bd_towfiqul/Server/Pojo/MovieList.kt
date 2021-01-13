package com.example.oceanize_bd_towfiqul.Server.Pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class MovieList {

    @SerializedName("results")
    @Expose
    var results: List<MovieList_>? = null

    fun getMovieList(): List<MovieList_>? {
        return results
    }

    fun setMovieList(movielist: List<MovieList_>?) {
        this.results = movielist
    }
}