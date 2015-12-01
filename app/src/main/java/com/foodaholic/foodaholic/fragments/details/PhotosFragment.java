package com.foodaholic.foodaholic.fragments.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.foodaholic.foodaholic.R;
import com.foodaholic.foodaholic.activity.PhotoFullViewActivity;
import com.foodaholic.foodaholic.adapter.PhotoAdapter;

/**
 * Created by maygupta on 11/28/15.
 */
public class PhotosFragment extends Fragment {

    GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.photos_fragment, container, false);

        gridView = (GridView) v.findViewById(R.id.gv_photos);
        gridView.setAdapter(new PhotoAdapter(this.getActivity()));

        setUpOnClickListener();

        return v;
    }

    public void setUpOnClickListener() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i = new Intent(getActivity(), PhotoFullViewActivity.class);
                i.putExtra("url", view.getTag().toString());
                startActivity(i);
            }
        });
    }
}
