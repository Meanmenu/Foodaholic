package com.foodaholic.foodaholic.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;
import com.foodaholic.foodaholic.R;
import com.foodaholic.foodaholic.fragments.details.DetailsFragment;
import com.foodaholic.foodaholic.fragments.details.PhotosFragment;
import com.foodaholic.foodaholic.fragments.details.ReviewsFragment;

/**
 * Created by carlos on 11/25/2015.
 */
public class DetailItemActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item_menu);
        toolbarCreation();

        final ViewPager vpPager = (ViewPager) findViewById(R.id.viewpager);
        FoodOptionsAdapter adapter = new FoodOptionsAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapter);
        vpPager.setOffscreenPageLimit(3);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Details"));
        tabLayout.addTab(tabLayout.newTab().setText("Photos"));
        tabLayout.addTab(tabLayout.newTab().setText("Reviews"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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

