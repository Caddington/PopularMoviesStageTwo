package com.caddington.dev.popularmovies;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.caddington.dev.popularmovies.model.Movie;

import java.util.List;

//ViewModel implementation adapted from Google's Android Room with a View codelab boilerplate: https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#8
public class MovieViewModel extends AndroidViewModel{

    private MovieRepository movieRepository;

    private LiveData<List<Movie>> allMovies;

    public MovieViewModel(Application application){
        super(application);

        movieRepository = new MovieRepository(application);
        allMovies = movieRepository.getAllMovies();
    }

    public void insert(Movie movie){
        movieRepository.insert(movie);
    }

    //GETTERS/SETTERS
    public LiveData<List<Movie>> getAllMovies() {
        return allMovies;
    }
}
