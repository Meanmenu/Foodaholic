package com.foodaholic.foodaholic;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by towu on 11/25/15.
 */
public class PlaceData implements Serializable{
    String name;
    String imageUrl;
    String description;
    double lon;
    double lat;
    List<String> reviews;
    float score; // 1.0~5.0

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public float getScore() {
        return score;
    }

    public static PlaceData fromJson(JSONObject json) {
        PlaceData place = new PlaceData();

        try {
            place.name = json.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return place;
    }

}
