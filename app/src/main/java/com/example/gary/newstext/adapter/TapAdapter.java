package com.example.gary.newstext.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Gary on 2017/9/24.
 */

public class TapAdapter extends FragmentPagerAdapter {
    private String[] mTitles;
    private Fragment[] mFragments;

    public TapAdapter(FragmentManager fm, Fragment[] fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    public void setmTitles(String[] mTitles) {
        this.mTitles = mTitles;
    }

    @Override
    public int getCount() {
        if (mFragments.length != 0 && mFragments != null) {
            return mFragments.length;
        } else
            return 0;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments[position % mFragments.length];
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitles.length != 0 && mTitles != null) {
            return mTitles[position % mFragments.length];
        } else
            return "";
    }
}
