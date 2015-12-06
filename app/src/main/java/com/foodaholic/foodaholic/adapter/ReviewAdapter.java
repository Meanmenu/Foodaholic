package com.foodaholic.foodaholic.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.foodaholic.foodaholic.R;
import com.foodaholic.foodaholic.model.Review;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by maygupta on 11/29/15.
 */
public class ReviewAdapter extends ArrayAdapter<Review> {

    // View lookup cache
    private static class ViewHolder {
        TextView reviewBody;
        ImageView reviewUserImageUrl;
        TextView reviewDate;
        TextView reviewScore;
        TextView reviewUsername;
    }

    public ReviewAdapter(Context context, List<Review> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder; // view lookup cache stored in tag
        Review review = getItem(position);

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_review, parent, false);

            viewHolder.reviewBody = (TextView) convertView.findViewById(R.id.tvReviewBody);
            viewHolder.reviewDate = (TextView) convertView.findViewById(R.id.tvReviewDate);
            viewHolder.reviewScore = (TextView) convertView.findViewById(R.id.tvReviewScore);
            viewHolder.reviewUsername = (TextView) convertView.findViewById(R.id.tvReviewUsername);
            viewHolder.reviewUserImageUrl = (ImageView) convertView.findViewById(R.id.ivUser);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.reviewBody.setText(review.getBody());
        viewHolder.reviewDate.setText(review.getDate());
        viewHolder.reviewScore.setText(String.valueOf(review.getScore()));
        viewHolder.reviewUsername.setText(review.getUsername());

        viewHolder.reviewUserImageUrl.setImageResource(android.R.color.transparent);
        Picasso.with(getContext()).load(review.getUserImageUrl()).into(viewHolder.reviewUserImageUrl);

        return convertView;
    }
}
