package com.foodaholic.foodaholic.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.foodaholic.foodaholic.R;
import com.squareup.picasso.Picasso;

public final class ImageFragment extends Fragment {
    private static final String IMAGE_ID = "IMAGE_ID";
    private String imageUri;

    public static ImageFragment newInstance(String imageResourceId){
        ImageFragment imageFragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putString(IMAGE_ID, imageResourceId);
        imageFragment.setArguments(args);
        return imageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_image, container, false);
        imageUri = getArguments().getString(IMAGE_ID);
        ImageView image = (ImageView) v.findViewById(R.id.image);
        Picasso.with(getActivity()).load(imageUri).fit().centerCrop().into(image);

        return v;
    }
}