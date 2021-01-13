package com.example.oceanize_bd_towfiqul.Server.Interface

import com.example.oceanize_bd_towfiqul.Server.Pojo.MovieList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieListAPI {

    @GET("/3/movie/popular?")
    fun getMovieList(@Query("api_key") api_key: String,
                        @Query("page") page: String): Observable<MovieList>?
}