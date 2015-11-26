package com.foodaholic.foodaholic.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.foodaholic.foodaholic.R;
import com.foodaholic.foodaholic.adapter.MenuItemAdapter;
import com.foodaholic.foodaholic.mock.MockMenuItem;
import com.foodaholic.foodaholic.model.MenuItem;
import com.foodaholic.foodaholic.util.ItemClickSupport;

import java.util.List;

public class MenuActivity extends BaseActivity {

    private MenuItemAdapter adapter;
    private List<MenuItem> menuItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        toolbarCreation();

        // Retrieving the RecyclerView from the fragment layout
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv_menu);
        // Setting a LinearLayoutManager as LayoutManager (Make it look like a ListView)
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);

        menuItemList = MockMenuItem.menuMock();
        adapter = new MenuItemAdapter(menuItemList, this);
        rv.setAdapter(adapter);

        ItemClickSupport.addTo(rv).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent i = new Intent(MenuActivity.this, DetailItemMenuActivity.class);
                MenuItem item = menuItemList.get(position);
                i.putExtra("item", item);
                startActivity(i);
            }
        });
    }


}
