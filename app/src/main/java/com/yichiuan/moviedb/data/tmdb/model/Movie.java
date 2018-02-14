package com.yichiuan.moviedb.data.tmdb.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@AutoValue
public abstract class Movie {

    public abstract String overview();

    @SerializedName("original_language")
    public abstract String originalLanguage();

    @SerializedName("original_title")
    public abstract String originalTitle();

    public abstract boolean video();

    public abstract String title();

    @SerializedName("genre_ids")
    public abstract List<Integer> genreIds();

    @SerializedName("poster_path")
    public abstract String posterPath();

    @SerializedName("backdrop_path")
    public abstract String backdropPath();

    @SerializedName("release_date")
    public abstract String releaseDate();

    @SerializedName("vote_average")
    public abstract double voteAverage();

    public abstract double popularity();

    public abstract int id();

    public abstract boolean adult();

    @SerializedName("vote_count")
    public abstract int voteCount();

    public static TypeAdapter<Movie> typeAdapter(Gson gson) {
        return new AutoValue_Movie.GsonTypeAdapter(gson);
    }
}