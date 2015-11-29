package com.foodaholic.foodaholic.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateUtils;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;


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
        Picasso.with(getContext()).load(Integer.valueOf(place.getImageUrl())).centerCrop().fit().into(ivPlaceImage);
        tvScore.setText("Rating: " + String.valueOf((int)place.getScore()));

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


    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }

}
