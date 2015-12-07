package com.foodaholic.foodaholic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.foodaholic.foodaholic.fragments.details.DetailsFragment;
import com.foodaholic.foodaholic.fragments.details.ReviewsFragment;
import com.foodaholic.foodaholic.model.Review;

public class FoodOptionsAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = {"Details", "Reviews"};
    private ReviewsFragment reviewsFragment;

    public FoodOptionsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if ( position == 0) {
            return new DetailsFragment();
        } else if (position == 1) {
            reviewsFragment = new ReviewsFragment();
            return reviewsFragment;
        } else {
            return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    public void addReview(Review r) {
        reviewsFragment.addReview(r);
    }
}