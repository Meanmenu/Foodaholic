package com.foodaholic.foodaholic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.foodaholic.foodaholic.R;
import com.foodaholic.foodaholic.fragments.ImageFragment;
import com.foodaholic.foodaholic.model.MenuItemData;

/**
 * Created by carlos on 11/30/2015.
 */
public class ImagesAdapter extends FragmentPagerAdapter {
    private MenuItemData item;
    public ImagesAdapter(FragmentManager fm, MenuItemData item) {
        super(fm);
        this.item = item;
    }

    @Override
    public Fragment getItem(int position) {
        return ImageFragment.newInstance(item.getPictureUrlList().get(position));
    }

    @Override
    public int getCount() {
        return item.getPictureUrlList().size();
    }

}
