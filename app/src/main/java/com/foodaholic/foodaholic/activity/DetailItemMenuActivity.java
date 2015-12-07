package com.foodaholic.foodaholic.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.foodaholic.foodaholic.R;
import com.foodaholic.foodaholic.adapter.FoodOptionsAdapter;
import com.foodaholic.foodaholic.adapter.ImagesAdapter;
import com.foodaholic.foodaholic.fragments.details.AddReviewFragment;
import com.foodaholic.foodaholic.fragments.details.ReviewsFragment;
import com.foodaholic.foodaholic.model.MenuItemData;
import com.foodaholic.foodaholic.model.Review;
import com.viewpagerindicator.CirclePageIndicator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailItemMenuActivity extends AppCompatActivity {
    public static final String ITEM = "item";
    public static final String REVIEWS = "Reviews";

    @Bind(R.id.viewpager) ViewPager vpPager;
    @Bind(R.id.nest_scrollview) NestedScrollView scrollView;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.tabs) TabLayout tabLayout;
    @Bind(R.id.indicator) CirclePageIndicator mIndicator;
    @Bind(R.id.float_btn) FloatingActionButton fab;
    private FoodOptionsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item_menu);
        ButterKnife.bind(this);

        MenuItemData item = getIntent().getExtras().getParcelable(ITEM);
        setActionBar(item);
        setToolbarImages(item);
        setTabs();
    }

    @OnClick(R.id.float_btn)
    public void submit(View view) {
        showAddReviewFragment(view);
    }

    public void showAddReviewFragment(View view) {
        FragmentManager fm = getFragmentManager();
        AddReviewFragment fragment = AddReviewFragment.newInstance();
        fragment.setListener(new ReviewsFragment.AddReviewListener() {
            @Override
            public void finish(Review r) {
                adapter.addReview(r);
            }
        });
        fragment.show(fm, "add_review");
    }

    private void setToolbarImages(MenuItemData item) {
        ImagesAdapter mAdapter = new ImagesAdapter(getSupportFragmentManager(), item);
        ViewPager mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
        mIndicator.setViewPager(mPager);
        mIndicator.setSnap(true);
    }

    private void setTabs() {
        scrollView.setFillViewport (true); // Needed to have tabs

        adapter = new FoodOptionsAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapter);
        vpPager.setOffscreenPageLimit(2);

        tabLayout.addTab(tabLayout.newTab().setText("Details"));
        tabLayout.addTab(tabLayout.newTab().setText(REVIEWS));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpPager.setCurrentItem(tab.getPosition());
                if(tab.getText().equals(REVIEWS)){
                    fab.setVisibility(View.VISIBLE);
                }else{
                    fab.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setActionBar(MenuItemData item) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("  " + item.getItemName());
    }

    public void handleCommentsClick(View view) {
        vpPager.setCurrentItem(1);
    }
    public void handlePhotosClick(View view) { vpPager.setCurrentItem(0);}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_gallery:
                Intent i = new Intent(this, GalleryActivity.class);
                startActivity(i);
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
