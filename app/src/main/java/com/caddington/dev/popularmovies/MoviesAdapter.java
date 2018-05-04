package com.caddington.dev.popularmovies;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>{

    private GridItemClickListener gridItemClickListener;

    public MoviesAdapter(GridItemClickListener gridItemClickListener){
        this.gridItemClickListener = gridItemClickListener;
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

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
