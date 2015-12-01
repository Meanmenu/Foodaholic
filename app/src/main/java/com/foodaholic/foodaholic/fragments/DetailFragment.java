package com.foodaholic.foodaholic.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public final class DetailFragment extends Fragment {
    private static final String IMAGE_ID = "IMAGE_ID";
    private int imageResourceId;

    public static DetailFragment newInstance(int imageResourceId){
        DetailFragment detailFragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(IMAGE_ID, imageResourceId);
        detailFragment.setArguments(args);
        return detailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        imageResourceId = getArguments().getParcelable(IMAGE_ID);
        ImageView image = new ImageView(getActivity());
        image.setImageResource(imageResourceId);

        LinearLayout layout = new LinearLayout(getActivity());

        layout.setGravity(Gravity.CENTER);
        layout.addView(image);

        return layout;
    }
}