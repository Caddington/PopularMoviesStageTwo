package com.caddington.dev.popularmovies.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.caddington.dev.popularmovies.model.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie ORDER BY title ASC")
    public LiveData<List<Movie>> getAllMovies();

    @Query("SELECT * FROM movie WHERE id = :id")
    LiveData<Movie> getMovieById(int id);

    @Insert
    public void insertMovie(Movie movie);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    public void updateMovie(Movie movie);

    @Delete
    public void deleteMovie(Movie movie);


}
