package com.caddington.dev.popularmovies;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.caddington.dev.popularmovies.databinding.ActivityMoviedetailsBinding;
import com.caddington.dev.popularmovies.model.Movie;
import com.caddington.dev.popularmovies.model.ReviewList;
import com.caddington.dev.popularmovies.model.TrailerList;
import com.caddington.dev.popularmovies.service.MovieService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity implements ReviewsAdapter.ReviewClickListener, TrailersAdapter.TrailerClickListener {

    Movie movie;

    MovieViewModel movieViewModel;

    ActivityMoviedetailsBinding binding;

    private static final String TAG = MovieDetailsActivity.class.getSimpleName();

    private TrailersAdapter trailersAdapter;
    private ReviewsAdapter reviewsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        //Android documentation's binding creation suggestion (https://developer.android.com/topic/libraries/data-binding/expressions#binding_data)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_moviedetails);

        if (getIntent().getParcelableExtra(movieViewModel.INTENT_EXTRA_KEY_MOVIE) != null){
            movie = (Movie) getIntent().getParcelableExtra(movieViewModel.INTENT_EXTRA_KEY_MOVIE);
        }

        if (getIntent().getParcelableExtra(movieViewModel.INTENT_EXTRA_KEY_FAVORITE) != null){
            binding.switchFavorite.setChecked(true);
        }

        //Switch toggle listener to add/remove movie from Room DB.
        binding.switchFavorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    movieViewModel.insertFavorite(movie);

                    Toast addFavoriteToast = Toast.makeText(getApplicationContext(), getString(R.string.toast_db_insert_favorite), Toast.LENGTH_SHORT);
                    addFavoriteToast.show();
                }else{
                    movieViewModel.deleteFavorite(movie);

                    Toast addFavoriteToast = Toast.makeText(getApplicationContext(), getString(R.string.toast_db_delete_favorite), Toast.LENGTH_SHORT);
                    addFavoriteToast.show();
                }
            }
        });


        loadPosters();
        loadText();

        setupRecyclerViews();

        fetchTrailers(movie.getId());
        fetchReviews(movie.getId());
    }

    private void loadPosters(){

        Uri smallPosterUri = Uri.parse(MovieService.BASE_POSTER_URL + MovieService.POSTER_SIZE_185 + movie.getPoster_path());

        Picasso.get()
                .load(smallPosterUri)
                //TMDb logo used as placeholder with permission, per https://www.themoviedb.org/about/logos-attribution.
                .placeholder(R.drawable.tmdb_placeholder)
                .fit()
                .into(binding.ivMoviePosterSmall);

        Uri backgroundPosterUri = Uri.parse(MovieService.BASE_POSTER_URL + MovieService.POSTER_SIZE_ORIGINAL + movie.getPoster_path());

        Picasso.get()
                .load(backgroundPosterUri)
                //TMDb logo used as placeholder with permission, per https://www.themoviedb.org/about/logos-attribution.
                .placeholder(R.drawable.tmdb_placeholder)
                .into(binding.ivMoviePosterBackground);
    }

    //Set TextViews from passed-in Movie model object.

    private void loadText(){
        binding.tvTitle.setText(movie.getTitle());
        binding.tvReleaseDate.setText(movie.getRelease_date());
        binding.tvVoteAverage.setText(String.valueOf(movie.getVote_average()));
        binding.tvOverview.setText(movie.getOverview());

    }
    private void setupRecyclerViews() {
        RecyclerView trailerRecyclerView = findViewById(R.id.details_trailer_recycler_view);
        LinearLayoutManager trailersLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        trailerRecyclerView.setLayoutManager(trailersLinearLayoutManager);
        trailerRecyclerView.setHasFixedSize(true);
        trailersAdapter = new TrailersAdapter(this);
        trailerRecyclerView.setAdapter(trailersAdapter);

        RecyclerView reviewsRecyclerView = findViewById(R.id.details_reviews_recycler_view);
        LinearLayoutManager reviewsLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        reviewsRecyclerView.setLayoutManager(reviewsLinearLayoutManager);
        reviewsRecyclerView.setHasFixedSize(true);
        reviewsAdapter = new ReviewsAdapter(this);
        reviewsRecyclerView.setAdapter(reviewsAdapter);
    }

    private void fetchTrailers(int movieId) {
        //Referred to Retrofit source documentation for constructing calls like this.
        MovieService movieService = MovieService.retrofit.create(MovieService.class);
        final Call<TrailerList> movieCall = movieService.movieTrailers(movieId, "");
        movieCall.enqueue(new Callback<TrailerList>() {
            @Override
            public void onResponse(Call<TrailerList> call, Response<TrailerList> response) {

                if (response.body() != null){
                    TrailerList trailerList = response.body();

                    //Set adapter movieId and trailer list properties, force rebind of all items.
                    trailersAdapter.setMovieId(trailerList.movieId);
                    trailersAdapter.setTrailers(trailerList.trailers);
                    trailersAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<TrailerList> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void fetchReviews(int movieId) {
        //Referred to Retrofit source documentation for constructing calls like this.
        MovieService movieService = MovieService.retrofit.create(MovieService.class);
        final Call<ReviewList> movieCall = movieService.movieReviews(movieId, "");
        movieCall.enqueue(new Callback<ReviewList>() {
            @Override
            public void onResponse(Call<ReviewList> call, Response<ReviewList> response) {

                if (response.body() != null){
                    ReviewList reviewList = response.body();

                    //Set adapter movieId and trailer list properties, force rebind of all items.
                    reviewsAdapter.setReviews(reviewList.reviews);
                    reviewsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ReviewList> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    @Override
    public void onReviewClick(int clickedItemIndex) {
        Log.d(TAG, "Review viewHolder clicked at position " + String.valueOf(clickedItemIndex));
    }

    @Override
    public void onTrailerClick(int clickedTrailerIndex) {
        Log.d(TAG, "Trailer viewHolder clicked at position " + String.valueOf(clickedTrailerIndex));

        Uri trailerUri = Uri.parse(MovieService.BASE_TRAILER_URL + trailersAdapter.getTrailers().get(clickedTrailerIndex).getKey());
        if (trailerUri != null){
            Intent trailerIntent = new Intent(Intent.ACTION_VIEW, trailerUri);
            startActivity(trailerIntent);
        }
    }
}
