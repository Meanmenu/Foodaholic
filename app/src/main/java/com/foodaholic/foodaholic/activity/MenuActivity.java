package com.foodaholic.foodaholic.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;
import com.foodaholic.foodaholic.R;
import com.foodaholic.foodaholic.fragments.menu.FoodTab1Fragment;
import com.foodaholic.foodaholic.fragments.menu.FoodTab2Fragment;
import com.foodaholic.foodaholic.fragments.menu.FoodTab3Fragment;

public class MenuActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        toolbarCreation();

        ViewPager vpPager = (ViewPager) findViewById(R.id.viewpager);
        MenuTypeAdapter adapter = new MenuTypeAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapter);
        vpPager.setOffscreenPageLimit(3);

        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabStrip.setViewPager(vpPager);
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

}
