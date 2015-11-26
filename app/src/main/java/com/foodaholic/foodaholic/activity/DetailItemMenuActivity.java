package com.foodaholic.foodaholic.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.foodaholic.foodaholic.R;
import com.foodaholic.foodaholic.model.MenuItem;
import com.squareup.picasso.Picasso;

/**
 * Created by carlos on 11/25/2015.
 */
public class DetailItemMenuActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item_menu);
        toolbarCreation();
        MenuItem item = getIntent().getExtras().getParcelable("item");

        TextView name = (TextView) findViewById(R.id.tv_item_menu);
        ImageView picture = (ImageView) findViewById(R.id.iv_item_menu);

        name.setText(item.getItemName());
        Picasso.with(this).load(Integer.valueOf(item.getPictureUrlList())).centerCrop().fit().into(picture);
    }
}
