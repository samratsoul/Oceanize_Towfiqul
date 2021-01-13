package com.example.oceanize_bd_towfiqul.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oceanize_bd_towfiqul.Server.Interface.MovieDetailAPI
import com.example.oceanize_bd_towfiqul.Server.NetworkClient
import com.example.oceanize_bd_towfiqul.Server.Pojo.MovieDetails
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieDetailsViewModel : ViewModel() {

    private val TAG = "MOVIE_DETAIL_VIEW_MODEL"
    lateinit var movieDetails : MutableLiveData<MovieDetails>
    var disposable: Disposable? = null

    fun init(url: String){
        movieDetails= MutableLiveData<MovieDetails>()
        fetchMovieDetailsFromServer(url)
    }

    fun fetchMovieDetailsFromServer(url: String) {
        val retrofit = NetworkClient.getRetrofit()

        val movieDetailAPI: MovieDetailAPI = retrofit.create(MovieDetailAPI::class.java)

        disposable = movieDetailAPI.getMovieDetails(url)
            ?.subscribeOn(Schedulers.io())
            ?.subscribe(
                { result ->
                    this.movieDetails.postValue(result)
                    disposeDB()
                },
                { error -> showError(error.message) }
            )
    }

    fun getMovieDetail() : LiveData<MovieDetails>{
        return movieDetails
    }
    private fun showError(message: String?) {
        Log.e(TAG, message.toString())
    }

    fun disposeDB(){
        disposable?.dispose()
    }

}