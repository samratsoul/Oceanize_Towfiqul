package com.example.oceanize_bd_towfiqul.Activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.oceanize_bd_towfiqul.BuildConfig
import com.example.oceanize_bd_towfiqul.R
import com.example.oceanize_bd_towfiqul.Server.Pojo.MovieDetails
import com.example.oceanize_bd_towfiqul.Util.Constants
import com.example.oceanize_bd_towfiqul.ViewModel.MovieDetailsViewModel

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        val id = intent.getStringExtra(Constants.MOVIE_ID)
        val url = "/3/movie/"+id+"?api_key="+BuildConfig.API_KEY
        val movieDetailsViewModel = MovieDetailsViewModel()
        movieDetailsViewModel.init(url)
        movieDetailsViewModel.getMovieDetail().observe(this, Observer<MovieDetails> { movieDetails ->
            setvalue(movieDetails)
        })

    }

    fun setvalue(movieDetails: MovieDetails){
        findViewById<TextView>(R.id.id_title).text= movieDetails.title
        findViewById<TextView>(R.id.id_title).typeface =  ResourcesCompat.getFont(this, R.font.monotype)
        findViewById<TextView>(R.id.id_rate).text= "Rate "+movieDetails.voteAverage.toString()
        findViewById<TextView>(R.id.id_length).text= movieDetails.runtime.toString() +" min"

        val genre= movieDetails.genres?.get(0)?.name;
        if(genre!=null) findViewById<TextView>(R.id.id_genre).text= genre

        findViewById<TextView>(R.id.id_release_date).text= movieDetails.releaseDate
        findViewById<TextView>(R.id.id_overview).text= movieDetails.overview
        findViewById<TextView>(R.id.id_tagline).text= movieDetails.tagline

        val country= movieDetails.productionCountries?.get(0)?.name;
        if(country!=null) findViewById<TextView>(R.id.id_country).text= country

        findViewById<TextView>(R.id.id_released).text= movieDetails.status
        findViewById<TextView>(R.id.id_language).text= movieDetails.originalLanguage
        val imageView = findViewById<ImageView>(R.id.id_poster)

        Glide.with(this)
            .load(Constants.IMAGE_URL+ movieDetails.posterPath)
            .centerInside()
            .placeholder(R.drawable.moviel_list_place_holer)
            .into(imageView)
    }
}