package com.foodaholic.foodaholic.activity;

import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.foodaholic.foodaholic.R;
import com.foodaholic.foodaholic.client.YelpAPI;
import com.foodaholic.foodaholic.fragments.places.PlacesListFragment;
import com.foodaholic.foodaholic.fragments.places.PlacesMapFragment;
import com.foodaholic.foodaholic.model.PlaceData;
import com.foodaholic.foodaholic.service.EddystoneScannerService;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlacesActivity extends BaseActivity implements PlacesMapFragment.OnFragmentInteractionListener {
    ArrayList<PlaceData> places;

    @Bind(R.id.viewpager) ViewPager viewPager;
    @Bind(R.id.tabs) TabLayout tabLayout;

    double lat;
    double lon;
    String location;
    LocationManager mLocationManager;

    String url = "https://api.yelp.com/v2/search/?term=food&ll=37.77493,-122.419415";
    YelpAPI yelpApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        ButterKnife.bind(this);

        toolbarCreation();
        getSupportActionBar().setTitle(R.string.app_name);

        // Get the viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new PlacesPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(2);

        tabLayout.addTab(tabLayout.newTab().setText("PLACES"));
        tabLayout.addTab(tabLayout.newTab().setText("MAP"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        Intent serviceIntent = new Intent(this, EddystoneScannerService.class);
        startService(serviceIntent);

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

//        String name = makeFragmentName(viewPager.getId(), 0);
//        PlacesListFragment fragment = (PlacesListFragment) getSupportFragmentManager().findFragmentByTag(name);
//        fragment.places = this.places;

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public class PlacesPagerAdapter extends FragmentPagerAdapter {
        private String tabTitles[] = {"Places", "Map"};

        public PlacesPagerAdapter (FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new PlacesListFragment();
            } else if (position == 1) {
                return new PlacesMapFragment();
            }

            return null;
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

    private static String makeFragmentName(int viewId, int position) {
        return "android:switcher:" + viewId + ":" + position;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_places, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatemen

        return super.onOptionsItemSelected(item);
    }
}
