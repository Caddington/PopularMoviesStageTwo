package com.caddington.dev.popularmovies.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import com.caddington.dev.popularmovies.model.Movie;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    private static final String TAG = MovieDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "movies";
    private static final Object LOCK = new Object();
    private static MovieDatabase dbInstance;

    public static MovieDatabase getInstance(Context context) {
        if (dbInstance == null) {
            synchronized (LOCK) {
                Log.d(TAG, "Creating new database instance");
                dbInstance = Room.databaseBuilder(context.getApplicationContext(),
                        MovieDatabase.class, MovieDatabase.DATABASE_NAME)
                        .build();
            }
        }
        Log.d(TAG, "Getting the database instance");
        return dbInstance;
    }
}
