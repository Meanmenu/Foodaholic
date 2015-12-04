package com.foodaholic.foodaholic.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by towu on 11/25/15.
 */
public class PlaceData implements Serializable{
    String id;
    String name;
    String imageUrl;
    String description;
    double lon;
    double lat;
    List<String> reviews;
    double score; // 1.0~5.0

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setScore(double score) {
        this.score = score;
    }

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

    public double getScore() {
        return score;
    }

    public static PlaceData fromJson(JSONObject json) {
        PlaceData place = new PlaceData();

        try {
            place.name = json.getString("name");
            place.score = json.getDouble("rating");
            place.imageUrl = json.getString("image_url").replace("ms.jpg", "l.jpg");
            place.description = json.getString("snippet_text");
            JSONObject location = json.getJSONObject("location");
            JSONObject coordinate = location.getJSONObject("coordinate");
            place.lat = coordinate.getDouble("latitude");
            place.lon = coordinate.getDouble("longitude");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return place;
    }

    public static ArrayList<PlaceData> fromJsonArray(JSONArray array) {
        ArrayList<PlaceData> places = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            try {
                places.add(PlaceData.fromJson(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
        }

        return places;
    }


}
