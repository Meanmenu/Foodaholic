package com.foodaholic.foodaholic.fragments.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.foodaholic.foodaholic.R;
import com.foodaholic.foodaholic.adapter.ReviewAdapter;
import com.foodaholic.foodaholic.mock.MockReviewList;
import com.foodaholic.foodaholic.model.Review;

import java.util.List;

/**
 * Created by maygupta on 11/28/15.
 */
public class ReviewsFragment extends Fragment {

    ReviewAdapter adapter;
    List<Review> reviews;
    ListView lvReviews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.reviews_fragment, container, false);

        lvReviews = (ListView) v.findViewById(R.id.lvReviews);
        reviews = MockReviewList.getList();
        adapter = new ReviewAdapter(this.getActivity(), reviews);
        lvReviews.setAdapter(adapter);

        return v;
    }
}
