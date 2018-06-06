package com.caddington.dev.popularmovies;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.caddington.dev.popularmovies.databinding.ActivityMoviedetailsBinding;
import com.caddington.dev.popularmovies.model.Movie;
import com.caddington.dev.popularmovies.service.MovieService;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    Movie movie;

    ActivityMoviedetailsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Android documentation's binding creation suggestion (https://developer.android.com/topic/libraries/data-binding/expressions#binding_data)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_moviedetails);

        if (getIntent().getParcelableExtra("movie") != null){
            movie = (Movie) getIntent().getParcelableExtra("movie");
        }

        loadPosters();
        loadText();
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

}
