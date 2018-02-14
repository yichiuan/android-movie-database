package com.yichiuan.moviedb.data.tmdb

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.yichiuan.moviedb.BuildConfig
import com.yichiuan.moviedb.data.tmdb.model.TMDbTypeAdapterFactory
import com.yichiuan.moviedb.data.tmdb.remote.TMDbService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class TMDbRepository {

    companion object {
        val API_KEY: String = "622272713580798879c94c15316d08af"
    }

    val tmDbService: TMDbService

    val okHttpClient: OkHttpClient

    val gson: Gson

    init {
        val httpClientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor { message -> Timber.tag("TMDb").d(message) }
            logging.level = HttpLoggingInterceptor.Level.BASIC

            httpClientBuilder.addInterceptor(logging)
                    .addNetworkInterceptor(StethoInterceptor())
        }

//        httpClientBuilder.connectTimeout(CONNECT_TIMEOUT_SECINDS.toLong(), TimeUnit.SECONDS)
        okHttpClient = httpClientBuilder.build()

        gson = GsonBuilder()
                .registerTypeAdapterFactory(TMDbTypeAdapterFactory.create())
                .create()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        tmDbService = retrofit.create(TMDbService::class.java)
    }

    fun getPopularMovies(page: Int) = tmDbService.getPopularMovies(page, API_KEY)
}