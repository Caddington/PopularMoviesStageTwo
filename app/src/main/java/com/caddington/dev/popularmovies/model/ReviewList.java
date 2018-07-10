package com.caddington.dev.popularmovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewList {

    @SerializedName("id")
    int movieId;

    @SerializedName("results")
    public List<Trailer> trailers;
}
