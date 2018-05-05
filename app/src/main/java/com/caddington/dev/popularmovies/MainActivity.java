package com.caddington.dev.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.GridItemClickListener{

    private RecyclerView movieRecyclerView;

    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieRecyclerView = findViewById(R.id.movies_recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        movieRecyclerView.setLayoutManager(gridLayoutManager);

        movieRecyclerView.setHasFixedSize(true);

        moviesAdapter = new MoviesAdapter(this);

        movieRecyclerView.setAdapter(moviesAdapter);
    }

    @Override
    public void onGridItemClick(int clickedItemIndex) {

    }
}
