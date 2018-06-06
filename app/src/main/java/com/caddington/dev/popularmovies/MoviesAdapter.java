package com.caddington.dev.popularmovies;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.caddington.dev.popularmovies.model.Movie;
import com.caddington.dev.popularmovies.model.MovieList;
import com.caddington.dev.popularmovies.service.MovieService;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>{

    private static final String TAG = MoviesAdapter.class.getSimpleName();

    private GridItemClickListener gridItemClickListener;
    private List<Movie> movies;

    public MoviesAdapter(GridItemClickListener gridItemClickListener){
        this.gridItemClickListener = gridItemClickListener;
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_grid_item, parent, false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(view);

        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Log.d(TAG, "viewHolder bound at position" + String.valueOf(position));

        if (movies != null){
            loadMoviePoster(holder.posterImageView, position);
        }
    }

    @Override
    public int getItemCount() {
        if (movies != null){
            return movies.size();
        }else{
            return 20;
        }
    }

    //Build URI for movie at List's index of position and load into its ImageView.
    private void loadMoviePoster(@NonNull ImageView posterImageView, int position) {

        /*Initialize to base poster URL. This is to handle the initial RecyclerView load when movies object is expected to be null (During background network call from MainActivity).
        Setting to this will force the placeholder image to appear until network retrieval finishes and calls notifyDatasetChanged to rebind the views with poster images.
         */
        Uri posterUri = Uri.parse(MovieService.BASE_POSTER_URL);

        if (movies != null) {
            posterUri = Uri.parse(MovieService.BASE_POSTER_URL + MovieService.POSTER_SIZE_185 + movies.get(position).getPoster_path());
        }

        Picasso.get()
                .load(posterUri)
                //TMDb logo used as placeholder with permission, per https://www.themoviedb.org/about/logos-attribution.
                .placeholder(R.drawable.tmdb_placeholder)
                .fit()
                .into(posterImageView);
    }

    //Setter used in MainActivity to pass retrieved movies.
    public void setMovies(MovieList movieList){
        this.movies = movieList.movies;
    }

    public List<Movie> getMovies(){
        return this.movies;
    }

    public interface GridItemClickListener {
        void onGridItemClick(int clickedItemIndex);
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView posterImageView;

        public MovieViewHolder(View itemView){
            super(itemView);

            posterImageView = (ImageView) itemView.findViewById(R.id.iv_poster);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            gridItemClickListener.onGridItemClick(getAdapterPosition());
        }
    }
}
