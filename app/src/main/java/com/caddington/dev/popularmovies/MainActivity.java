package com.caddington.dev.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.caddington.dev.popularmovies.database.MovieDatabase;
import com.caddington.dev.popularmovies.model.MovieList;
import com.caddington.dev.popularmovies.service.MovieService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.GridItemClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();

    private MoviesAdapter moviesAdapter;

    private MovieDatabase movieDatabase;

    //Enter your TMDb API key here for app to work.
    private String apiKey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize database
        movieDatabase = MovieDatabase.getInstance(this);

        RecyclerView movieRecyclerView = findViewById(R.id.movies_recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        movieRecyclerView.setLayoutManager(gridLayoutManager);
        movieRecyclerView.setHasFixedSize(true);
        moviesAdapter = new MoviesAdapter(this);
        movieRecyclerView.setAdapter(moviesAdapter);

        queryMovies(MovieService.MOVIE_SORT_POPULAR);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mainactivity, menu);

        return true;
    }

    //Handle user selecting "Popular" or "Top Rated" from ActionBar overflow menu. Query depending on which was tapped.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.sort_popular:
                queryMovies(MovieService.MOVIE_SORT_POPULAR);
                break;
            case R.id.sort_toprated:
                queryMovies(MovieService.MOVIE_SORT_TOPRATED);
                break;
            default:
                break;
        }

        return true;
    }

    //TODO: Move network call to Repository
    //Handle querying movies to keep onCreate clean. Accepts sort type as parameter
    private void queryMovies(String sortOrder) {
        //Referred to Retrofit source documentation for constructing calls like this.
        MovieService movieService = MovieService.retrofit.create(MovieService.class);
        final Call<MovieList> movieCall = movieService.moviesSorted(sortOrder, apiKey);
        movieCall.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {

                if (response.body() != null){
                    MovieList movieList = response.body();

                    //Set adapter movie list property and force rebind of all grid items.
                    moviesAdapter.setMovies(movieList);
                    moviesAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    @Override
    public void onGridItemClick(int clickedItemIndex) {

        if (moviesAdapter.getMovies() != null){
            Intent intent = new Intent(this, MovieDetailsActivity.class);
            intent.putExtra(getString(R.string.INTENT_EXTRA_KEY_MOVIE), moviesAdapter.getMovies().get(clickedItemIndex));
            startActivity(intent);
        }
    }
}

