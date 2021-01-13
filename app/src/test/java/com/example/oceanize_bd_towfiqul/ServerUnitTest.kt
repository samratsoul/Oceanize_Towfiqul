package com.example.oceanize_bd_towfiqul

import android.util.Log
import com.example.oceanize_bd_towfiqul.Server.Interface.MovieListAPI
import com.example.oceanize_bd_towfiqul.Server.NetworkClient
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.junit.Test

class ServerUnitTest {

    private val TAG = "UNIT_TESTING"

    @Test
    fun server_isWorking() {
        var disposable: Disposable? = null
        val retrofit = NetworkClient.getRetrofit()

        val movieListAPI: MovieListAPI = retrofit.create(MovieListAPI::class.java)

        disposable = movieListAPI.getMovieList(BuildConfig.API_KEY, "1")
            ?.subscribeOn(Schedulers.io())
            ?.subscribe(
                { result ->  Log.i(TAG,"Server responding")
                    disposable?.dispose()
                } ,
                { error -> Log.i(TAG,"Server has error")
                    disposable?.dispose()}
            )
    }

    @Test
    fun server_hasItems() {
        var disposable: Disposable? = null
        val retrofit = NetworkClient.getRetrofit()

        val movieListAPI: MovieListAPI = retrofit.create(MovieListAPI::class.java)

        disposable = movieListAPI.getMovieList(BuildConfig.API_KEY, "1")
            ?.subscribeOn(Schedulers.io())
            ?.subscribe(
                { result ->  Log.i(TAG,"Item: "+ result.getMovieList()!!.size)
                    disposable?.dispose()
                } ,
                { error -> Log.i(TAG,"Server has error")
                    disposable?.dispose()}
            )
    }
}