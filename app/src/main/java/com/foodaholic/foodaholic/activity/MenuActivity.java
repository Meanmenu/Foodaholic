package com.foodaholic.foodaholic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.foodaholic.foodaholic.R;
import com.foodaholic.foodaholic.fragments.menu.FoodTab1Fragment;
import com.foodaholic.foodaholic.fragments.menu.FoodTab2Fragment;
import com.foodaholic.foodaholic.fragments.menu.FoodTab3Fragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MenuActivity extends BaseActivity {

    @Bind(R.id.tabs) TabLayout tabLayout;
    @Bind(R.id.viewpager) ViewPager vpPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

        toolbarCreation();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Pearl's Deluxe"); //TODO Update this dynamically
        ButterKnife.bind(this);

        MenuTypeAdapter adapter = new MenuTypeAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapter);
        vpPager.setOffscreenPageLimit(3);

        tabLayout.addTab(tabLayout.newTab().setText("Appetizers"));
        tabLayout.addTab(tabLayout.newTab().setText("Salads"));
        tabLayout.addTab(tabLayout.newTab().setText("Sandwiches"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    public class MenuTypeAdapter extends FragmentPagerAdapter {
        private String tabTitles[] = {"Appetizers", "Salads", "Sandwiches"};

        public MenuTypeAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if ( position == 0) {
                return new FoodTab1Fragment();
            } else if (position == 1) {
                return new FoodTab2Fragment();
            }  else if (position == 2) {
                return new FoodTab3Fragment();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
