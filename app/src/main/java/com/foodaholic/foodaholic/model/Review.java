package com.foodaholic.foodaholic.model;

import android.util.Log;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by maygupta on 11/29/15.
 */
@ParseClassName("Review")
public class Review extends ParseObject implements Serializable {
    private double score;
    private String username;
    private String userImageUrl;
    private String date;
    private String body;

    public Review(double score, String username, String userImageUrl, String date, String body) {
        this.score = score;
        this.username = username;
        this.userImageUrl = userImageUrl;
        this.date = date;
        this.body = body;
        put("score", String.valueOf(score));
        put("username", username);
        put("userImageUrl", userImageUrl);
        put("body", body);
        put("food_item_id", "1");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        put("date", sdf.format(new Date()));
        saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.d("ERROR", e.getMessage().toString());
                }
            }
        });
    }

    public Review(){}

    public Review(ParseObject object) {
        this.score = Double.parseDouble(object.getString("score"));
        this.userImageUrl = object.getString("userImageUrl");
        this.body = object.getString("body");
        this.username = object.getString("username");
        this.date = object.getString("date");
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
