package com.example.oceanize_bd_towfiqul.Server

import com.example.oceanize_bd_towfiqul.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClient {

    companion object {
        @Volatile
        private var INSTANCE: Retrofit? = null

        fun getRetrofit(): Retrofit {

            return INSTANCE ?: synchronized(this) {
                val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BuildConfig.BASE_HOST)
                    .build()

                INSTANCE = retrofit
                // return instance
                retrofit
            }
        }
    }
}