package com.caddington.dev.popularmovies;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.TrailerViewHolder>{

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

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public interface TrailerClickListener{
        void onTrailerClick(int clickedTrailerIndex);
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
