package com.example.oceanize_bd_towfiqul.Server.Pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieList_ {

    @SerializedName("id")
    @Expose
    var id: Int? = 0

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("adult")
    @Expose
    var adult: Boolean? = false

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null

    @SerializedName("genre_ids")
    @Expose
    var genreIds: IntArray? = null

    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null

    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null

    @SerializedName("overview")
    @Expose
    var overview: String? = null

    @SerializedName("popularity")
    @Expose
    var popularity: Float? = 0f

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null

    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null

    @SerializedName("video")
    @Expose
    var video: String? = null

    @SerializedName("vote_average")
    @Expose
    var voteAverage: Float? = 0f

    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = 0
}