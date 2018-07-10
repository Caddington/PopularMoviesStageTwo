package com.caddington.dev.popularmovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailerList {

    @SerializedName("id")
    public int movieId;

    @SerializedName("results")
    public List<Trailer> trailers;
}
