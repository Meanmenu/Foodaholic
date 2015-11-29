package com.foodaholic.foodaholic.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;
import com.foodaholic.foodaholic.R;
import com.foodaholic.foodaholic.fragments.details.DetailsFragment;
import com.foodaholic.foodaholic.fragments.details.PhotosFragment;
import com.foodaholic.foodaholic.fragments.details.ReviewsFragment;

/**
 * Created by carlos on 11/25/2015.
 */
public class DetailItemMenuActivity extends BaseActivity{

    private ViewPager vpPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item_menu);
        toolbarCreation();

        vpPager = (ViewPager) findViewById(R.id.viewpager);
        FoodOptionsAdapter adapter = new FoodOptionsAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapter);
        vpPager.setOffscreenPageLimit(3);

        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabStrip.setViewPager(vpPager);
    }

    public void handlePhotosClick(View view) {
        vpPager.setCurrentItem(1);
    }

    public void handleCommentsClick(View view) {
        vpPager.setCurrentItem(2);
    }

    public class FoodOptionsAdapter extends FragmentPagerAdapter {
        private String tabTitles[] = {"Details", "Photos", "Reviews"};

        public FoodOptionsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if ( position == 0) {
                return new DetailsFragment();
            } else if (position == 1) {
                return new PhotosFragment();
            }  else if (position == 2) {
                return new ReviewsFragment();
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
    }
}
