package com.foodaholic.foodaholic.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
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

public class PlacesActivity extends BaseActivity implements PlacesMapFragment.OnFragmentInteractionListener, LocationListener {
    ArrayList<PlaceData> places;

    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.tabs)
    TabLayout tabLayout;

    double lat;
    double lon;
    Location location;
    LocationManager locationManager;

    String url = "https://api.yelp.com/v2/search/?term=food&ll=37.77493,-122.419415";
    YelpAPI yelpApi;

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            //your code here
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

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

        getLocation();
        //mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        String name = makeFragmentName(viewPager.getId(), 0);
//        PlacesListFragment fragment = (PlacesListFragment) getSupportFragmentManager().findFragmentByTag(name);
//        fragment.places = this.places;

    }

    public void getLocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // getting GPS status
        boolean isGPSEnabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        // getting network status
        boolean isNetworkEnabled = locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!isGPSEnabled && !isNetworkEnabled) {
            // no network provider is enabled
        } else {
            //this.canGetLocation = true;
            if (isNetworkEnabled) {
                locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        60000,
                        10, this);
                Log.d("activity", "LOC Network Enabled");
                if (locationManager != null) {
                    location = locationManager
                            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (location != null) {
                        Log.d("activity", "LOC by Network");
                        lat = location.getLatitude();
                        lon = location.getLongitude();
                    }
                }
            }
            // if GPS Enabled get lat/long using GPS Services
            if (isGPSEnabled) {
                if (location == null) {
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            60000,
                            10, this);
                    Log.d("activity", "RLOC: GPS Enabled");
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (location != null) {
                            Log.d("activity", "RLOC: loc by GPS");

                            lat = location.getLatitude();
                            lon = location.getLongitude();
                        }
                    }
                }
            }
        }
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onLocationChanged(Location location) {
        lat = location.getLatitude();
        lon = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public class PlacesPagerAdapter extends FragmentPagerAdapter {
        private String tabTitles[] = {"Places", "Map"};

        public PlacesPagerAdapter (FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                // TODO: use configurable variable for name and radius
                return PlacesListFragment.newInstance("food", lat, lon, 1000);
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
