package com.foodaholic.foodaholic.fragments.places;

import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.foodaholic.foodaholic.R;
import com.foodaholic.foodaholic.adapter.PlacesArrayAdapter;
import com.foodaholic.foodaholic.client.YelpAPI;
import com.foodaholic.foodaholic.model.PlaceData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "name";
    private static final String ARG_PARAM2 = "lat";
    private static final String ARG_PARAM3 = "lon";

    // TODO: Rename and change types of parameters
    private String name; // currently hard coded as food
    private double lat;
    private double lon;

    private OnFragmentInteractionListener mListener;
    private YelpAPI yelpApi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_places_list, container, false);
        lvPlaces = (ListView) v.findViewById(R.id.lvPlaces);
        lvPlaces.setAdapter(aPlaces);

        return v;

    }

    // TODO: Rename and change types of parameters
    public static PlacesListFragment newInstance(String name, double lat, double lon) {
        PlacesListFragment fragment = new PlacesListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, name);
        args.putDouble(ARG_PARAM2, lat);
        args.putDouble(ARG_PARAM3, lon);
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
        }

        aPlaces = new PlacesArrayAdapter(getActivity(), places);

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                YelpAPI yelp = YelpAPI.getYelpClient();
                // TODO: get current location and call yelp.searchByCoordinate
                String businesses = yelp.searchByCoordinate(name, lat, lon);
                try {
                    return processJson(businesses);
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

    String processJson(String jsonStuff) throws JSONException {
        JSONObject json = new JSONObject(jsonStuff);
        JSONArray businesses = json.getJSONArray("businesses");
        places.clear();
        places.addAll(PlaceData.fromJsonArray(businesses));
        return String.valueOf(json.getInt("total"));
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
        mListener = null;
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
