package com.caddington.dev.popularmovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewList {

    @SerializedName("results")
    public List<Review> reviews;
}
