package com.caddington.dev.popularmovies;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caddington.dev.popularmovies.model.Review;

import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder>{

    private static final String TAG = ReviewsAdapter.class.getSimpleName();

    private ReviewClickListener reviewClickListener;

    private List<Review> reviews;

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
        if (reviews != null && !reviews.isEmpty()){
            Log.d(TAG, reviews.get(position).getContent());
            holder.reviewTextView.setText(reviews.get(position).getContent());
        }
    }

    @Override
    public int getItemCount() {
        if (reviews != null) {
            return reviews.size();
        } else {
            return 4;
        }
    }

    public interface ReviewClickListener {
        void onReviewClick(int clickedItemIndex);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView reviewTextView;

        public ReviewViewHolder(View itemView) {
            super(itemView);

            reviewTextView = (TextView) itemView.findViewById(R.id.tv_review);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            reviewClickListener.onReviewClick(getAdapterPosition());
        }
    }
}
