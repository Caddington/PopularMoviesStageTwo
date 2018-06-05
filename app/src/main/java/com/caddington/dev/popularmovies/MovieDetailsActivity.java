package com.caddington.dev.popularmovies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.caddington.dev.popularmovies.model.Movie;

public class MovieDetailsActivity extends AppCompatActivity {

    Movie movie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_moviedetails);

        if (getIntent().getParcelableExtra("movie") != null){
            movie = (Movie) getIntent().getParcelableExtra("movie");
        }
    }

}
