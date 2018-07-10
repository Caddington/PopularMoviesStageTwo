package com.caddington.dev.popularmovies;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder>{

    private static final String TAG = ReviewsAdapter.class.getSimpleName();

    private ReviewClickListener reviewClickListener;

    public ReviewsAdapter(ReviewClickListener reviewClickListener) {
        this.reviewClickListener = reviewClickListener;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.review_item, parent, false);
        ReviewsAdapter.ReviewViewHolder reviewViewHolder = new ReviewsAdapter.ReviewViewHolder(view);

        return reviewViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Log.d(TAG, "Review viewHolder bound at position" + String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public interface ReviewClickListener {
        void onReviewClick(int clickedItemIndex);
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public ReviewViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            reviewClickListener.onReviewClick(getAdapterPosition());
        }
    }
}
