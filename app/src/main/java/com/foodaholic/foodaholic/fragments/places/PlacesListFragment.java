package com.foodaholic.foodaholic.fragments.places;

import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.foodaholic.foodaholic.R;
import com.foodaholic.foodaholic.adapter.PlacesArrayAdapter;
import com.foodaholic.foodaholic.client.LocuAPI;
import com.foodaholic.foodaholic.client.YelpAPI;
import com.foodaholic.foodaholic.model.PlaceData;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.github.jlmd.animatedcircleloadingview.AnimatedCircleLoadingView;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class PlacesListFragment extends Fragment implements LocationListener {
    public ArrayList<PlaceData> places = new ArrayList<>();
    public PlacesArrayAdapter aPlaces;
    protected ListView lvPlaces;
    AnimatedCircleLoadingView animatedCircleLoadingView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "name";
    private static final String ARG_PARAM2 = "lat";
    private static final String ARG_PARAM3 = "lon";
    private static final String ARG_PARAM4 = "radius";

    private String name; // currently hard coded as food
    private double lat;
    private double lon;
    private long radius; // hard coded as 1000 meters

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_places_list, container, false);
        lvPlaces = (ListView) v.findViewById(R.id.lvPlaces);
        aPlaces = new PlacesArrayAdapter(getActivity(), places);
        lvPlaces.setAdapter(aPlaces);
        animatedCircleLoadingView = (AnimatedCircleLoadingView) v.findViewById(R.id.circle_loading_view);
        animatedCircleLoadingView.startIndeterminate();

        loadPlaces();

        return v;
    }

    // TODO: Rename and change types of parameters
    public static PlacesListFragment newInstance(String name, double lat, double lon, long radius) {
        PlacesListFragment fragment = new PlacesListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, name);
        args.putDouble(ARG_PARAM2, lat);
        args.putDouble(ARG_PARAM3, lon);
        args.putLong(ARG_PARAM4, radius);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PlacesListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            name = getArguments().getString(ARG_PARAM1);
            lat = getArguments().getDouble(ARG_PARAM2);
            lon = getArguments().getDouble(ARG_PARAM3);
            radius = getArguments().getLong(ARG_PARAM4);
        }

        aPlaces = new PlacesArrayAdapter(getActivity(), places);

    }
    public void loadPlaces() {
        //getPlacesFromYelp();

        getPlacesFromLocu();
    }
    private void getPlacesFromYelp() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                YelpAPI yelp = YelpAPI.getYelpClient();
                // TODO: get current location and call yelp.searchByCoordinate
                String businesses = yelp.searchByCoordinate(name, lat, lon, radius);
                SystemClock.sleep(5000);
                try {
                    return processJsonYelp(businesses);
                } catch (JSONException e) {
                    return businesses;
                }
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                animatedCircleLoadingView.stopOk();
                animatedCircleLoadingView.setVisibility(View.GONE);
                aPlaces.notifyDataSetChanged();
            }

        }.execute();
    }

    private void fillPlacesFromYelp(final List<PlaceData> places) {
        for( final PlaceData place : places) {
            new AsyncTask<Void, Void, String>() {
                @Override
                protected String doInBackground(Void... params) {
                    YelpAPI yelp = YelpAPI.getYelpClient();
                    // TODO: get current location and call yelp.searchByCoordinate
                    String businesses = yelp.searchByCoordinate(place.getName(), place.getLat(), place.getLon(), 100);
                    try {
                        String result = processJsonYelpMerge(businesses, place);
                        if (result.equals("0")) {
                            places.remove(place);
                        }
                        return result;
                    } catch (JSONException e) {
                        return businesses;
                    }
                }
                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    aPlaces.notifyDataSetChanged();

                }

            }.execute();

        }
    }

    private void getPlacesFromLocu() {
        LocuAPI locu = LocuAPI.getLocuClient();
        final YelpAPI yelp = YelpAPI.getYelpClient();

        locu.fetchVenues("restaurants", lat, lon, 500, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray venues = response.getJSONArray("venues");
                    places.clear();
                    places.addAll(PlaceData.fromJsonArrayLocu(venues));
                    fillPlacesFromYelp(places);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });

    }

    String processJsonYelp(String jsonStuff) throws JSONException {
        JSONObject json = new JSONObject(jsonStuff);
        JSONArray businesses = json.getJSONArray("businesses");
        places.clear();
        places.addAll(PlaceData.fromJsonArrayYelp(businesses));
        return String.valueOf(json.getInt("total"));
    }

    String processJsonYelpMerge(String jsonStuff, PlaceData place) throws JSONException {
        JSONObject json = new JSONObject(jsonStuff);
        JSONArray businesses = json.getJSONArray("businesses");
        if (businesses.length() == 0) {
            return "0";
        }
        else {
            PlaceData.fromJsonYelpMerge(businesses.getJSONObject(0), place);
            return String.valueOf(json.getInt("total"));
        }
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onLocationChanged(Location location) {
        lat = location.getLatitude();
        lon = location.getLongitude();
        Log.d("Location changed.", "lat="+lat);
        Log.d("Location changed.", "lon="+lon);
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

//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        super.on
//        super.onListItemClick(l, v, position, id);
//
//        if (null != mListener) {
//            // Notify the active callbacks interface (the activity, if the
//            // fragment is attached to one) that an item has been selected.
//            mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
//        }
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

}
