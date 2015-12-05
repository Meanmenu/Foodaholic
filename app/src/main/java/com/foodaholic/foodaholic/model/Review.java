package com.foodaholic.foodaholic.model;

import com.foodaholic.foodaholic.R;

import java.io.Serializable;

/**
 * Created by maygupta on 11/29/15.
 */
public class Review implements Serializable {
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
