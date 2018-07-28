package com.caddington.dev.popularmovies;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.caddington.dev.popularmovies.database.MovieDao;
import com.caddington.dev.popularmovies.database.MovieDatabase;
import com.caddington.dev.popularmovies.model.Movie;

import java.util.List;

public class MovieRepository {

    private MovieDao movieDao;
    private LiveData<List<Movie>> allMovies;

    public MovieRepository(Application application){
        MovieDatabase movieDatabase = MovieDatabase.getInstance(application)
        movieDao = movieDatabase.movieDao();
        allMovies = movieDao.getAllMovies();
    }

}
