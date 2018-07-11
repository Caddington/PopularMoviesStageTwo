package com.caddington.dev.popularmovies;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.caddington.dev.popularmovies.model.Trailer;
import com.caddington.dev.popularmovies.service.MovieService;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.TrailerViewHolder>{

    private static final String TAG = TrailersAdapter.class.getSimpleName();

    private List<Trailer> trailers;

    private int movieId;

    private TrailerClickListener trailerClickListener;

    public TrailersAdapter(TrailerClickListener trailerClickListener) {
        this.trailerClickListener = trailerClickListener;
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.trailer_item, parent, false);
        TrailersAdapter.TrailerViewHolder trailerViewHolder = new TrailersAdapter.TrailerViewHolder(view);

        return trailerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {
        Log.d(TAG, "Trailer viewHolder bound at position" + String.valueOf(position));

        if (trailers != null){
            Log.d(TAG, "Number of trailers for movie " + movieId + ": " + trailers.size());

            loadMovieTrailer(holder.trailerImageView, position);
        }

    }

    @Override
    public int getItemCount() {
        if (trailers != null) {
            return trailers.size();
        }else{
            return 4;
        }
    }

    public interface TrailerClickListener{

        void onTrailerClick(int clickedTrailerIndex);
    }

    private void loadMovieTrailer(ImageView trailerImageView, int position) {

        Uri posterUri = Uri.parse(MovieService.BASE_TRAILER_URL);

        if (trailers != null) {
            posterUri = Uri.parse(MovieService.BASE_TRAILER_URL + trailers.get(position).getKey() + MovieService.TRAILER_IMAGE);

        }
        Picasso.get()
                .load(posterUri)
                //TMDb logo used as placeholder with permission, per https://www.themoviedb.org/about/logos-attribution.
                .placeholder(R.drawable.tmdb_placeholder)
                .fit()
                .into(trailerImageView);
    }

    public List<Trailer> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    class TrailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView trailerImageView;

        public TrailerViewHolder(View itemView) {
            super(itemView);

            trailerImageView = (ImageView) itemView.findViewById(R.id.iv_trailer);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            trailerClickListener.onTrailerClick(getAdapterPosition());
        }
    }
}
