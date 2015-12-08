package com.foodaholic.foodaholic.client;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class LocuAPI {
    private static final String API_HOST = "api.locu.com";
    private static final String DEFAULT_TERM = "restaurants";
    private static final String DEFAULT_LOCATION = "San Francisco, CA";
    private static final int SEARCH_LIMIT = 10;
    private static final String SEARCH_PATH = "/v2/venue/search";

    private static final String API_KEY = "7a3860482635d66772dfccd955d0d1c11cf54e8e";

    AsyncHttpClient client;
    private static LocuAPI locu;

    public static LocuAPI getLocuClient() {
        if (locu == null) {
            locu = new LocuAPI();
        }
        return locu;
    }

    public LocuAPI() {
        client = new AsyncHttpClient();
    }

    public static JSONObject generatePostSearchRestaurants(String category, double lat, double lon, long radius) {
        JSONObject json = new JSONObject();
        JSONArray fields = new JSONArray();
        JSONArray venue_queries = new JSONArray();
        JSONObject venue_query = new JSONObject();
        JSONObject categories = new JSONObject();
        JSONObject location = new JSONObject();
        JSONObject geo = new JSONObject();
        JSONArray geo_value = new JSONArray();
        try {
            json.put("api_key", API_KEY);

            fields.put("locu_id");
            fields.put("name");
            fields.put("location");
            fields.put("open_hours");
            json.put("fields", fields);

            categories.put("str_id", "restaurants");
            venue_query.put("categories", categories);

            geo_value.put(lat);
            geo_value.put(lon);
            geo_value.put(radius);
            geo.put("$in_lat_lng_radius", geo_value);
            location.put("geo", geo);
            venue_query.put("location", location);
            venue_queries.put(venue_query);
            json.put("venue_queries", venue_queries);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    public void fetchVenues(String category, Double lat, Double lon, long radius, JsonHttpResponseHandler handler){
        String url = "https://" + API_HOST + SEARCH_PATH;
        Log.i(getClass().getSimpleName(), url);
        JSONObject json = generatePostSearchRestaurants(category, lat, lon, radius);
        StringEntity entity = null;
        try {
            entity = new StringEntity(json.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        client.post(null, url, entity, "application/json", handler);
    }

}
