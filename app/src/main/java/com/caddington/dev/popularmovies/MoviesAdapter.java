package com.caddington.dev.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>{

    private static final String TAG = MoviesAdapter.class.getSimpleName();

    private GridItemClickListener gridItemClickListener;

    private int numViewHolders;

    public MoviesAdapter(GridItemClickListener gridItemClickListener){
        this.gridItemClickListener = gridItemClickListener;
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_grid_item, parent, false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(view);

        numViewHolders++;
        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: "
                + numViewHolders);

        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Log.d(TAG, "viewHolder bound at position" + String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public interface GridItemClickListener {
        void onGridItemClick(int clickedItemIndex);
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public MovieViewHolder(View itemView){
            super(itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
