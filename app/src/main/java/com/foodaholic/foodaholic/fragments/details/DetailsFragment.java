package com.foodaholic.foodaholic.fragments.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.foodaholic.foodaholic.R;
import com.foodaholic.foodaholic.model.MenuItemData;

/**
 * Created by maygupta on 11/28/15.
 */
public class DetailsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.details_fragment, container, false);

        MenuItemData item = getActivity().getIntent().getExtras().getParcelable("item");

        TextView score = (TextView) v.findViewById(R.id.tv_score);
        TextView likesCount = (TextView) v.findViewById(R.id.tv_like_count);
        TextView reviewsCount = (TextView) v.findViewById(R.id.tv_reviews_count);
        TextView photosCount = (TextView) v.findViewById(R.id.tv_photos_count);
        TextView type = (TextView) v.findViewById(R.id.tv_type);

        String typeText = item.getType();
        type.setText(typeText);
        if (typeText.equals("Vegetarian")) {
            type.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        } else if (typeText.equals("Non Vegetarian")) {
            type.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        } else if (typeText.equals("Vegan")) {
            type.setTextColor(getResources().getColor(android.R.color.holo_orange_dark));
        }

        score.setText(item.getScore());
        likesCount.setText("30");
        reviewsCount.setText("10");
        photosCount.setText("5");
        return v;
    }
}
