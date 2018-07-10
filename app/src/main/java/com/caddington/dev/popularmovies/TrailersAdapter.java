package com.caddington.dev.popularmovies;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caddington.dev.popularmovies.model.Trailer;

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
        View view = inflater.inflate(R.layout.review_item, parent, false);
        TrailersAdapter.TrailerViewHolder trailerViewHolder = new TrailersAdapter.TrailerViewHolder(view);

        return trailerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {
        Log.d(TAG, "Trailer viewHolder bound at position" + String.valueOf(position));

        if (trailers != null){
            Log.d(TAG, "Number of trailers for movie " + movieId + ": " + trailers.size());
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


        public TrailerViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            trailerClickListener.onTrailerClick(getAdapterPosition());
        }
    }
}
