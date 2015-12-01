package com.foodaholic.foodaholic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.foodaholic.foodaholic.R;
import com.foodaholic.foodaholic.fragments.DetailFragment;

/**
 * Created by carlos on 11/30/2015.
 */
public class ImagesAdapter extends FragmentPagerAdapter {

    private int[] images = new int[] { R.drawable.pearl_deluxe_burguer, R.drawable.teriyaki_burguer};
    private int mCount = images.length;
    public ImagesAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return DetailFragment.newInstance(images[position]);
    }

    @Override
    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        if (count > 0 && count <= 10) {
            mCount = count;
            notifyDataSetChanged();
        }
    }
}
