package com.example.oceanize_bd_towfiqul.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.example.oceanize_bd_towfiqul.BuildConfig
import com.example.oceanize_bd_towfiqul.Database.Model.MovieListData
import com.example.oceanize_bd_towfiqul.Database.MovieListDao
import com.example.oceanize_bd_towfiqul.Database.MovieListDatabase
import com.example.oceanize_bd_towfiqul.Server.Interface.MovieListAPI
import com.example.oceanize_bd_towfiqul.Server.NetworkClient
import com.example.oceanize_bd_towfiqul.Server.Pojo.MovieList
import com.example.oceanize_bd_towfiqul.Server.Pojo.MovieList_
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


class MainActivityViewModel : ViewModel() {

    private val TAG = "MIAN_ACTVTY_VIEW_MODEL"
    private lateinit var applicationScope: CoroutineScope
    private lateinit var database: MovieListDatabase
    private lateinit var movieListDao: MovieListDao
    var disposable: Disposable? = null

    fun init(context: Context) {
        dabaseInit(context)
        fetchDataFromServerToDB(BuildConfig.API_KEY, "1")
    }

    fun dabaseInit(context: Context) {
        applicationScope = CoroutineScope(SupervisorJob())
        database = MovieListDatabase.getDatabase(context, applicationScope)
        movieListDao = database.movieListDao()
    }

    fun getMovieList(): LiveData<PagedList<MovieListData>> {
        val movieListData: LiveData<PagedList<MovieListData>> =
            movieListDao.getMovieList().toLiveData(pageSize = 10)
        return movieListData;
    }

    fun fetchDataFromServerToDB(apiKey: String, page: String) {
        val retrofit = NetworkClient.getRetrofit()

        val movieListAPI: MovieListAPI = retrofit.create(MovieListAPI::class.java)

        disposable = movieListAPI.getMovieList(apiKey, page)
            ?.subscribeOn(Schedulers.io())
            ?.subscribe(
                { result -> if(putResultToDB(result)){
                    Log.i(TAG,"Database entry successfull")
                }},
                { error -> showError(error.message) }
            )
    }

    private fun showError(message: String?) {
        Log.e(TAG, message.toString())
    }

    private fun putResultToDB(result: MovieList): Boolean {
        var isSuccessful: Boolean = false

        for (movieList in result.getMovieList()!!) {
            movieListDao.insert(copyMovieListData(movieList))
            isSuccessful = true
        }

        disposeDB()
        return isSuccessful
    }

    fun copyMovieListData(movieList: MovieList_): MovieListData {

        val movieListData = MovieListData()
        movieListData.adult = movieList.adult
        movieListData.id = movieList.id
        movieListData.title = movieList.title
        movieListData.backdropPath = movieList.backdropPath
        movieListData.genreIds = movieList.genreIds.toString()
        movieListData.originalLanguage = movieList.originalLanguage
        movieListData.originalTitle = movieList.originalTitle
        movieListData.overview = movieList.overview
        movieListData.popularity = movieList.popularity
        movieListData.posterPath = movieList.posterPath
        movieListData.releaseDate = movieList.releaseDate
        movieListData.video = movieList.video
        movieListData.voteAverage = movieList.voteAverage
        movieListData.voteCount = movieList.voteCount

        return movieListData

    }

    fun disposeDB(){
        disposable?.dispose()
    }

    fun cleardatabase(){
        movieListDao.deleteAll()
    }
}

