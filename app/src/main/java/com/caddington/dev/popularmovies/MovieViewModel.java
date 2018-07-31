package com.caddington.dev.popularmovies;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.caddington.dev.popularmovies.model.Movie;
import com.caddington.dev.popularmovies.service.MovieService;

import java.util.List;

//ViewModel implementation adapted from Google's Android Room with a View codelab boilerplate: https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#8
public class MovieViewModel extends AndroidViewModel{

    private MovieRepository movieRepository;

    private LiveData<List<Movie>> favoriteMovies;

    private String sortOrder = MovieService.MOVIE_SORT_POPULAR;

    public MovieViewModel(Application application){
        super(application);

        movieRepository = new MovieRepository(application);
        favoriteMovies = movieRepository.getFavoriteMovies();
    }

    public void insert(Movie movie){
        movieRepository.insert(movie);
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
