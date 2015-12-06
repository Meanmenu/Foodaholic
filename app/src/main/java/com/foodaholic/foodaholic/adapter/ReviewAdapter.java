package com.foodaholic.foodaholic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.foodaholic.foodaholic.R;
import com.foodaholic.foodaholic.model.Review;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by maygupta on 11/29/15.
 */
public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private List<Review> mReviews;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        TextView reviewBody;
        ImageView reviewUserImageUrl;
        TextView reviewDate;
        TextView reviewScore;
        TextView reviewUsername;
        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            reviewBody = (TextView) itemView.findViewById(R.id.tvReviewBody);
            reviewDate = (TextView) itemView.findViewById(R.id.tvReviewDate);
            reviewScore = (TextView) itemView.findViewById(R.id.tvReviewScore);
            reviewUsername = (TextView) itemView.findViewById(R.id.tvReviewUsername);
            reviewUserImageUrl = (ImageView) itemView.findViewById(R.id.ivUser);
        }
    }

        // Pass in the contact array into the constructor
    public ReviewAdapter(List<Review> reviews,
                         Context context) {
        this.mReviews = reviews;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_review, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Review review = mReviews.get(position);
        viewHolder.reviewBody.setText(review.getBody());
        viewHolder.reviewDate.setText(review.getDate());
        viewHolder.reviewScore.setText(String.valueOf(review.getScore()));
        viewHolder.reviewUsername.setText(review.getUsername());

        viewHolder.reviewUserImageUrl.setImageResource(android.R.color.transparent);
        Picasso.with(mContext).load(review.getUserImageUrl()).into(viewHolder.reviewUserImageUrl);
    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

}
