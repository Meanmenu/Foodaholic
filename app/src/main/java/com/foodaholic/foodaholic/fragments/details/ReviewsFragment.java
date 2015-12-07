package com.foodaholic.foodaholic.fragments.details;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foodaholic.foodaholic.R;
import com.foodaholic.foodaholic.adapter.ReviewAdapter;
import com.foodaholic.foodaholic.model.Review;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maygupta on 11/28/15.
 */
public class ReviewsFragment extends Fragment {

    ReviewAdapter adapter;
    List<Review> reviews;
    RecyclerView lvReviews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.reviews_fragment, container, false);
        reviews = new ArrayList<>();
        lvReviews = (RecyclerView) v.findViewById(R.id.lvReviews);
        adapter = new ReviewAdapter(reviews, this.getContext());
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        lvReviews.setLayoutManager(llm);
        lvReviews.setAdapter(adapter);

        loadReviews();
        return v;
    }



    private void loadReviews() {

        ParseQuery<Review> query = ParseQuery.getQuery(Review.class);
        // TODO: This should be actual food_item_id
        query.whereEqualTo("food_item_id", "1");
        query.addDescendingOrder("date");
        query.findInBackground(new FindCallback<Review>() {
            @Override
            public void done(List<Review> objects, ParseException e) {
                for (ParseObject object : objects) {
                    Review r = new Review(object);
                    reviews.add(r);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void addReview(Review r) {
        reviews.add(0,r);
        adapter.notifyDataSetChanged();;
    }

    public interface AddReviewListener {
        public void finish(Review r);
    }

}
