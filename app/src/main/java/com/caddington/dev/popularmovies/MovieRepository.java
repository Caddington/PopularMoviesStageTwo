package com.caddington.dev.popularmovies;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.caddington.dev.popularmovies.database.MovieDao;
import com.caddington.dev.popularmovies.database.MovieDatabase;
import com.caddington.dev.popularmovies.model.Movie;

import java.util.List;

//Repository implementation adapted from Google's Android Room with a View codelab boilerplate: https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#7
public class MovieRepository {

    private MovieDao movieDao;

    private LiveData<List<Movie>> favoriteMovies;

    public MovieRepository(Application application){
        MovieDatabase movieDatabase = MovieDatabase.getInstance(application);
        movieDao = movieDatabase.movieDao();
        favoriteMovies = movieDao.getFavoriteMovies();
    }

    public void insert (Movie movie){
        new insertAsyncTask(movieDao).execute(movie);
    }

    private static class insertAsyncTask extends AsyncTask<Movie, Void, Void>{

        private MovieDao asyncTaskDao;

        insertAsyncTask(MovieDao movieDao){
            asyncTaskDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            asyncTaskDao.insertMovie(movies[0]);
            return null;
        }
    }

    //GETTERS/SETTERS
    public LiveData<List<Movie>> getFavoriteMovies() {
        return favoriteMovies;
    }
}
