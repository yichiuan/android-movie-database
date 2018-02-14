package com.yichiuan.moviedb.data.tmdb.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@AutoValue
public abstract class Movies {

    public abstract int page();

    @SerializedName("total_pages")
    public abstract int totalPages();

    public abstract List<Movie> results();

    @SerializedName("total_results")
    public abstract int totalResults();

    public static TypeAdapter<Movies> typeAdapter(Gson gson) {
        return new AutoValue_Movies.GsonTypeAdapter(gson);
    }
}