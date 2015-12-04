package com.foodaholic.foodaholic.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.foodaholic.foodaholic.R;
import com.foodaholic.foodaholic.activity.MenuActivity;
import com.foodaholic.foodaholic.model.PlaceData;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by towu on 11/25/15.
 */
public class PlacesArrayAdapter extends ArrayAdapter<PlaceData> {
    private Context context;
    public PlacesArrayAdapter(Context context, List<PlaceData> places) {
        super(context, android.R.layout.simple_list_item_1, places);
        this.context = context;
    }

    // TODO: ViewHolder pattern
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PlaceData place = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_place, parent, false);
        }

        ImageView ivPlaceImage = (ImageView) convertView.findViewById(R.id.ivPlaceImage);
        TextView tvPlaceName = (TextView) convertView.findViewById(R.id.tvPlaceName);
        TextView tvScore = (TextView) convertView.findViewById(R.id.tvReviewScore);

        tvPlaceName.setText(place.getName());
        ivPlaceImage.setImageResource(android.R.color.transparent);
        Picasso.with(getContext()).load(place.getImageUrl()).fit().into(ivPlaceImage);
        tvScore.setText("Rating: " + String.valueOf(place.getScore()));

        ivPlaceImage.setTag(place.getName());

        ivPlaceImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String placeName = v.getTag().toString();
                Intent i = new Intent(context, MenuActivity.class);
                i.putExtra("screen_name", placeName);
                context.startActivity(i);
            }
        });

        return convertView;

    }

}
