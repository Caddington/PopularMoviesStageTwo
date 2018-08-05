package com.caddington.dev.popularmovies;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.caddington.dev.popularmovies.model.Movie;
import com.caddington.dev.popularmovies.service.MovieService;

import java.util.List;

//ViewModel implementation adapted from Google's Android Room with a View codelab boilerplate: https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#8
public class MovieViewModel extends AndroidViewModel{

    public static final int QUERY_IS_FAVORITE = 1;
    public static final int QUERY_NOT_FAVORITE = -1;

    public static final String INTENT_EXTRA_KEY_MOVIE = "movie";
    public static final String INTENT_EXTRA_KEY_FAVORITE = "favorite";

    private MovieRepository movieRepository;

    private LiveData<List<Movie>> favoriteMovies;

    private String sortOrder = MovieService.MOVIE_SORT_POPULAR;

    public MovieViewModel(Application application){
        super(application);

        movieRepository = new MovieRepository(application);
        favoriteMovies = movieRepository.getFavoriteMovies();
    }

    public void insertFavorite(Movie movie){
        movieRepository.insertFavorite(movie);
    }

    public void deleteFavorite(Movie movie){
        movieRepository.deleteFavorite(movie);
    }

    //GETTERS/SETTERS
    //LiveData wrapper
    public LiveData<List<Movie>> getFavoriteMovies() {
        return favoriteMovies;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
}
