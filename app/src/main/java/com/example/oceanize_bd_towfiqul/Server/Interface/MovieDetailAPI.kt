package com.example.oceanize_bd_towfiqul.Server.Interface

import com.example.oceanize_bd_towfiqul.Server.Pojo.MovieDetails
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url


interface MovieDetailAPI {

    @GET
    fun getMovieDetails(@Url url: String?): Observable<MovieDetails>?

}