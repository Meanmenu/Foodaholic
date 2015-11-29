package com.foodaholic.foodaholic.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.foodaholic.foodaholic.R;
import com.foodaholic.foodaholic.model.MenuItem;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.MenuItemHolder>{

    private List<MenuItem> menuItemList;
    private FragmentActivity context;

    public MenuItemAdapter(List<MenuItem> menuItemList, FragmentActivity context) {
        this.context = context;
        this.menuItemList = menuItemList;
    }

    @Override
    public MenuItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        return new MenuItemHolder(v);
    }

    @Override
    public void onBindViewHolder(MenuItemHolder holder, int position) {
        final MenuItem menuItem = menuItemList.get(position);
        holder.name.setText(menuItem.getItemName());
        Picasso.with(context).load(Integer.valueOf(menuItem.getPictureUrlList())).centerCrop().fit().into(holder.image);
        holder.score.setText(menuItem.getScore());
    }

    @Override
    public int getItemCount() {
        return menuItemList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class MenuItemHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView image;
        public TextView score;
        public TextView cuisine;

        private MenuItemHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_item_menu);
            image = (ImageView) itemView.findViewById(R.id.iv_item_menu);
            score = (TextView) itemView.findViewById(R.id.tv_score);
        }

    }
}