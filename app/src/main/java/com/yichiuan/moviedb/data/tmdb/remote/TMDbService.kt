package com.yichiuan.moviedb.data.tmdb.remote

import com.yichiuan.moviedb.data.tmdb.model.Movies
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDbService {
    @GET("movie/popular/")
    fun getPopularMovies(@Query("page") page: Int, @Query("api_key") apiKey: String): Single<Movies>
}
