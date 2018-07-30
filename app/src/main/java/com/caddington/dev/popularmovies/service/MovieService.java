package com.caddington.dev.popularmovies.service;

import com.caddington.dev.popularmovies.model.MovieList;
import com.caddington.dev.popularmovies.model.ReviewList;
import com.caddington.dev.popularmovies.model.TrailerList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    String BASE_URL = "https://api.themoviedb.org/3/";
    String BASE_POSTER_URL = "http://image.tmdb.org/t/p/";

    String BASE_TRAILER_URL = "https://img.youtube.com/vi/";
    String TRAILER_IMAGE = "/0.jpg";

    String POSTER_SIZE_185 = "w185";
    String POSTER_SIZE_ORIGINAL = "original";

    String MOVIE_SORT_POPULAR = "popular";
    String MOVIE_SORT_TOPRATED = "top_rated";
    String MOVIE_SORT_FAVORITE = "favorite";

    @GET("movie/{ordering}")
    Call<MovieList> moviesSorted(@Path("ordering") String ordering, @Query("api_key") String apiKey);

    @GET("movie/{id}/videos")
    Call<TrailerList> movieTrailers(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("movie/{id}/reviews")
    Call<ReviewList> movieReviews(@Path("id") int id, @Query("api_key") String apiKey);

    /*  Implement a static Retrofit builder in the interface itself, as seen in https://zeroturnaround.com/rebellabs/getting-started-with-retrofit-2/
        Chose this rather than building it in MainActivity to further abstract network operations from the activity itself.
     */
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
