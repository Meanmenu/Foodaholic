package com.foodaholic.foodaholic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.foodaholic.foodaholic.R;
import com.foodaholic.foodaholic.adapter.PhotoAdapter;
import com.foodaholic.foodaholic.model.MenuItemData;

/**
 * Created by carlos on 12/1/2015.
 */
public class GalleryActivity extends BaseActivity {
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        toolbarCreation();
        getSupportActionBar().setTitle("Photos");

        MenuItemData item = getIntent().getExtras().getParcelable("item");
        gridView = (GridView) findViewById(R.id.gv_photos);
        gridView.setAdapter(new PhotoAdapter(this, item.getPictureUrlList()));

        setUpOnClickListener();
    }

    public void setUpOnClickListener() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i = new Intent(GalleryActivity.this, PhotoFullViewActivity.class);
                i.putExtra("url", view.getTag().toString());
                startActivity(i);
            }
        });
    }
}
