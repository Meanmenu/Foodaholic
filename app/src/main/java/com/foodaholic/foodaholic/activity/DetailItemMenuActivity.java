package com.foodaholic.foodaholic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
<<<<<<< HEAD
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Toast;
=======
import android.view.View;
>>>>>>> d80d9d1b4b94a828a3540275e0401ad48e8df51f

import com.astuetz.PagerSlidingTabStrip;
import com.bumptech.glide.Glide;
import com.foodaholic.foodaholic.R;
import com.foodaholic.foodaholic.adapter.ImagesAdapter;
import com.foodaholic.foodaholic.fragments.details.DetailsFragment;
import com.foodaholic.foodaholic.fragments.details.PhotosFragment;
import com.foodaholic.foodaholic.fragments.details.ReviewsFragment;
import com.squareup.picasso.Picasso;

public class DetailItemMenuActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "cheese_name";

    private ViewPager vpPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail);

        NestedScrollView scrollView = (NestedScrollView) findViewById (R.id.nest_scrollview);
        scrollView.setFillViewport (true);

        Intent intent = getIntent();
        final String cheeseName = intent.getStringExtra(EXTRA_NAME);

<<<<<<< HEAD
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(cheeseName);

        final ViewPager vpPager = (ViewPager) findViewById(R.id.viewpager);
=======
        vpPager = (ViewPager) findViewById(R.id.viewpager);
>>>>>>> d80d9d1b4b94a828a3540275e0401ad48e8df51f
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


        ImagesAdapter mAdapter = new ImagesAdapter(getSupportFragmentManager());

        ViewPager mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

//        CirclePageIndicator mIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
//        mIndicator.setViewPager(mPager);
//        ((CirclePageIndicator) mIndicator).setSnap(true);
//
//        mIndicator
//                .setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                    @Override
//                    public void onPageSelected(int position) {
//                        Toast.makeText(DetailItemMenuActivity.this,
//                                "Changed to page " + position,
//                                Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onPageScrolled(int position,
//                                               float positionOffset, int positionOffsetPixels) {
//                    }
//
//                    @Override
//                    public void onPageScrollStateChanged(int state) {
//                    }
//                });
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
                Log.i("DetailsFragment", "DetailsFragment");
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
