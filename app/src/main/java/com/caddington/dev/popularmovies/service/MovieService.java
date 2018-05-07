package com.caddington.dev.popularmovies.service;

import retrofit2.Retrofit;

public interface MovieService {

    private static final String BASE_URL = "https://api.themoviedb.org/3/"

    public static final Retrofit retrofit = new Retrofit().newBuilder()
            .baseUrl(BASE_URL)

}
