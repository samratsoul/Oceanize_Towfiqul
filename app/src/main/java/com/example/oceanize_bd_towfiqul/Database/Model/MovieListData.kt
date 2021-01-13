package com.example.oceanize_bd_towfiqul.Database.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "movie_list", indices = arrayOf(Index(value = ["id", "title"], unique = true)))
class MovieListData {

    @PrimaryKey(autoGenerate = true)
    var uid = 0

    @ColumnInfo(name = "id")
    var id: Int? = 0

    @ColumnInfo(name = "title")
    var title: String? = null

    @ColumnInfo(name = "adult")
    var adult: Boolean? = false

    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String? = null

    @ColumnInfo(name = "genre_ids")
    var genreIds: String? = null

    @ColumnInfo(name = "original_language")
    var originalLanguage: String? = null

    @ColumnInfo(name = "original_title")
    var originalTitle: String? = null

    @ColumnInfo(name = "overview")
    var overview: String? = null

    @ColumnInfo(name = "popularity")
    var popularity: Float? = 0f

    @ColumnInfo(name = "poster_path")
    var posterPath: String? = null

    @ColumnInfo(name = "release_date")
    var releaseDate: String? = null

    @ColumnInfo(name = "video")
    var video: String? = null

    @ColumnInfo(name = "vote_average")
    var voteAverage: Float? = 0f

    @ColumnInfo(name = "vote_count")
    var voteCount: Int? = 0
}