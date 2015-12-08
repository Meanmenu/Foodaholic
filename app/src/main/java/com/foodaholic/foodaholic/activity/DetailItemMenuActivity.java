package com.foodaholic.foodaholic.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.foodaholic.foodaholic.R;
import com.foodaholic.foodaholic.adapter.FoodOptionsAdapter;
import com.foodaholic.foodaholic.adapter.ImagesAdapter;
import com.foodaholic.foodaholic.fragments.details.AddReviewFragment;
import com.foodaholic.foodaholic.fragments.details.ReviewsFragment;
import com.foodaholic.foodaholic.model.MenuItemData;
import com.foodaholic.foodaholic.model.Review;
import com.viewpagerindicator.CirclePageIndicator;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailItemMenuActivity extends AppCompatActivity {
    public static final String ITEM = "item";
    public static final String REVIEWS = "Reviews";
    public static final String USERNAME = "username";
    public static final String ID = "id";
    public static final String PICTURE = "picture";

    @Bind(R.id.viewpager) ViewPager vpPager;
    @Bind(R.id.nest_scrollview) NestedScrollView scrollView;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.tabs) TabLayout tabLayout;
    @Bind(R.id.indicator) CirclePageIndicator mIndicator;
    @Bind(R.id.float_btn) FloatingActionButton fab;
    private FoodOptionsAdapter adapter;
    private CallbackManager callbackManager;
    private static Profile profile;
    private ProfileTracker mProfileTracker;
    AddReviewFragment fragment;

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (callbackManager != null && callbackManager.onActivityResult(requestCode, resultCode, data)) {
            return;
        }
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
           if (resultCode == RESULT_OK) {
             Uri takenPhotoUri = getPhotoFileUri(photoFileName);
             // by this point we have the camera photo on disk
             Bitmap bmp = BitmapFactory.decodeFile(takenPhotoUri.getPath());
             // Load the taken image into a preview
             Bitmap takenImage = Bitmap.createScaledBitmap(bmp, 400, 300, true);

             fragment.setReview(takenImage);
           } else { // Result was a failure
               Toast.makeText(this, "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
           }
        }
    }

    @OnClick(R.id.float_btn)
    public void showAddReviewFragment(View view) {
        final SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(DetailItemMenuActivity.this);

        if(mSettings.getString(ID, null) != null){
            startReviw();
        }else {
            callbackManager = CallbackManager.Factory.create();
            LoginManager mLoginMgr = LoginManager.getInstance();

            FacebookCallback<LoginResult> mFacebookCallback =
                    new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {
                            if (Profile.getCurrentProfile() == null) {
                                mProfileTracker = new ProfileTracker() {
                                    @Override
                                    protected void onCurrentProfileChanged(Profile profile, Profile profile2) {
                                        SharedPreferences.Editor editor = mSettings.edit();
                                        editor.putString(USERNAME, profile2.getFirstName() + " " + profile2.getLastName());
                                        editor.putString(ID, profile2.getId());
                                        editor.commit();
                                        mProfileTracker.stopTracking();
                                        startReviw();
                                    }
                                };
                                mProfileTracker.startTracking();
                            } else {
                                Profile profile = Profile.getCurrentProfile();
                                SharedPreferences.Editor editor = mSettings.edit();
                                editor.putString(USERNAME, profile.getFirstName() + " " + profile.getLastName());
                                editor.putString(ID, profile.getId());
                                editor.commit();
                                startReviw();
                            }
                        }

                        @Override
                        public void onCancel() {
                            // App code
                        }

                        @Override
                        public void onError(FacebookException exception) {
                            // App code
                        }
                    };

            List<String> mPermissions = Arrays.asList("public_profile, email, user_birthday, user_friends");
            mLoginMgr.registerCallback(callbackManager, mFacebookCallback);
            mLoginMgr.logInWithReadPermissions(this, mPermissions);
        }
    }

    private void startReviw() {
        FragmentManager fm = getFragmentManager();
        fragment = AddReviewFragment.newInstance();
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
                if (tab.getText().equals(REVIEWS)) {
                    fab.setVisibility(View.VISIBLE);
                } else {
                    fab.setVisibility(View.GONE);
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

                i.putExtra("item", getIntent().getExtras().getParcelable(ITEM));
                startActivity(i);
                return true;
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public final String APP_TAG = "MyCustomApp";
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
    public String photoFileName = "photo.jpg";

    public void onLaunchCamera(View view) {
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getPhotoFileUri(photoFileName)); // set the image file name

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Start the image capture intent to take photo
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
    }


    // Returns the Uri for a photo stored on disk given the fileName
    public Uri getPhotoFileUri(String fileName) {
        // Only continue if the SD Card is mounted
        if (isExternalStorageAvailable()) {
            // Get safe storage directory for photos
            File mediaStorageDir = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), APP_TAG);

            // Create the storage directory if it does not exist
            if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
                Log.d(APP_TAG, "failed to create directory");
            }

            // Return the file target for the photo based on filename
            return Uri.fromFile(new File(mediaStorageDir.getPath() + File.separator + fileName));
        }
        return null;
    }

    private boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }
}
