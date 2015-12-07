package com.foodaholic.foodaholic;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.foodaholic.foodaholic.model.Review;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by maygupta on 12/5/15.
 */
public class FoodMenuApplication extends Application {

    public static final String YOUR_APPLICATION_ID = "XcceZnIiQm82MviG5QbLdlW5a3k9PZpVhOW6FUh0";
    public static final String YOUR_CLIENT_KEY = "wG5Td4MpHht2sSSEu7XFVljJHCCKq7F2fEJmfKQ0";

    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Review.class);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, YOUR_APPLICATION_ID, YOUR_CLIENT_KEY);
        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
