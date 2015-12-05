package com.foodaholic.foodaholic.activity;

import android.content.Intent;
import android.os.Bundle;
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
import com.foodaholic.foodaholic.model.MenuItemData;
import com.viewpagerindicator.CirclePageIndicator;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailItemMenuActivity extends AppCompatActivity {
    public static final String ITEM = "item";

    @Bind(R.id.viewpager) ViewPager vpPager;
    @Bind(R.id.nest_scrollview) NestedScrollView scrollView;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.tabs) TabLayout tabLayout;
    @Bind(R.id.indicator) CirclePageIndicator mIndicator;

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

    private void setToolbarImages(MenuItemData item) {
        ImagesAdapter mAdapter = new ImagesAdapter(getSupportFragmentManager(), item);
        ViewPager mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
        mIndicator.setViewPager(mPager);
        mIndicator.setSnap(true);
    }

    private void setTabs() {
        scrollView.setFillViewport (true); // Needed to have tabs

        FoodOptionsAdapter adapter = new FoodOptionsAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapter);
        vpPager.setOffscreenPageLimit(2);

        tabLayout.addTab(tabLayout.newTab().setText("Details"));
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

    private void setActionBar(MenuItemData item) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("  " + item.getItemName());
    }

    public void handleCommentsClick(View view) {
        vpPager.setCurrentItem(1);
    }

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
