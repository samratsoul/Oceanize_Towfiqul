package com.example.oceanize_bd_towfiqul.Database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.oceanize_bd_towfiqul.Database.Model.MovieListData

@Dao
interface MovieListDao {
    // The flow always holds/caches latest version of data. Notifies its observers when the
    // data has changed.
    @Query("SELECT * FROM movie_list ORDER BY id ASC")
    fun getMovieList(): DataSource.Factory<Int, MovieListData>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movielist: MovieListData)

    @Query("DELETE FROM movie_list")
    fun deleteAll()
}