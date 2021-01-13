package com.example.oceanize_bd_towfiqul.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oceanize_bd_towfiqul.Adapter.MovieListAdapter
import com.example.oceanize_bd_towfiqul.R
import com.example.oceanize_bd_towfiqul.Util.Constants
import com.example.oceanize_bd_towfiqul.Util.RecyclerItemClickListener
import com.example.oceanize_bd_towfiqul.ViewModel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var movieListView : RecyclerView
    private lateinit var movieListAdapter : MovieListAdapter
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    fun init(){
        mainActivityViewModel= MainActivityViewModel()
        mainActivityViewModel.init(this)
        movieListView = findViewById(R.id.rv_movieList)
        movieListAdapter = MovieListAdapter()
        mainActivityViewModel.getMovieList().observe(this, Observer(movieListAdapter::submitList))
        movieListView.adapter= movieListAdapter
        linearLayoutManager= LinearLayoutManager(this)
        movieListView.layoutManager=linearLayoutManager
        movieListView.addOnItemTouchListener((RecyclerItemClickListener(this, movieListView, object : RecyclerItemClickListener.OnItemClickListener {

            override fun onItemClick(view: View, position: Int) {
                intent = Intent(applicationContext, MovieDetailsActivity::class.java)
                val id = movieListAdapter.currentList?.get(position)?.id
                if(id!=null) {
                    intent.putExtra(Constants.MOVIE_ID,id.toString())
                    startActivity(intent)
                }

            }
            override fun onItemLongClick(view: View?, position: Int) {
                TODO("do nothing")
            }
        })))


    }
}