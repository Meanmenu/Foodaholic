package com.foodaholic.foodaholic.fragments.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foodaholic.foodaholic.R;
import com.foodaholic.foodaholic.activity.DetailItemMenuActivity;
import com.foodaholic.foodaholic.adapter.MenuItemAdapter;
import com.foodaholic.foodaholic.model.MenuItemData;
import com.foodaholic.foodaholic.util.ItemClickSupport;

import java.util.List;

/**
 * Created by maygupta on 11/28/15.
 */
public abstract class FoodFragment extends Fragment {

    private MenuItemAdapter adapter;
    private List<MenuItemData> menuItemList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.food_menu_layout, container, false);

        // Retrieving the RecyclerView from the fragment layout
        RecyclerView rv = (RecyclerView) v.findViewById(R.id.rv_menu);
        // Setting a LinearLayoutManager as LayoutManager (Make it look like a ListView)
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        rv.setLayoutManager(linearLayoutManager);

        menuItemList = getMenuItemList();
        adapter = new MenuItemAdapter(menuItemList, this.getActivity());
        rv.setAdapter(adapter);

        ItemClickSupport.addTo(rv).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent i = new Intent(getActivity(), DetailItemMenuActivity.class);
                MenuItemData item = menuItemList.get(position);
                i.putExtra("item", item);
                startActivity(i);
            }
        });
        return v;
    }

    protected abstract List<MenuItemData> getMenuItemList();
}
