package com.caddington.dev.popularmovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieList {

    /*Referenced solution here to accept the response as a MovieList object with a List<Movie> property. This enables handling the MovieDb response which is a JSONObject at its root structure.
     *https://stackoverflow.com/questions/36177629/retrofit2-android-expected-begin-array-but-was-begin-object-at-line-1-column-2
     */
    @SerializedName("results")
    public List<Movie> movies;

    public MovieList(List<Movie> movies){}
}
