package com.caddington.dev.popularmovies.service;

import com.caddington.dev.popularmovies.model.MovieList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {


    String BASE_URL = "https://api.themoviedb.org/3/";
    String BASE_POSTER_URL = "http://image.tmdb.org/t/p/";

    String POSTER_SIZE_185 = "w185";
    String POSTER_SIZE_ORIGINAL = "original";

    String MOVIE_SORT_POPULAR = "popular";
    String MOVIE_SORT_TOPRATED = "top_rated";

    @GET("movie/{ordering}")
    Call<MovieList> moviesSorted(@Path("ordering") String ordering, @Query("api_key") String apiKey);

    /*  Implement a static Retrofit builder in the interface itself, as seen in https://zeroturnaround.com/rebellabs/getting-started-with-retrofit-2/
        Chose this rather than building it in MainActivity to further abstract network operations from the activity itself.
     */
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
