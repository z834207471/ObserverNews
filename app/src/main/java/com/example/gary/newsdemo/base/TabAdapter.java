package com.example.gary.newsdemo.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Gary on 2017/9/12.
 */

public class TabAdapter extends FragmentPagerAdapter {
    private Fragment[] fragments;
    private String[] mTitles;

    public TabAdapter(FragmentManager fm, Fragment[] fragments) {
        super(fm);
        this.fragments = fragments;
    }

    public void setTitles(String[] mTitles) {
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position % fragments.length];
    }

    @Override
    public int getCount() {
        if (fragments == null || fragments.length == 0)
            return 0;
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitles.length != 0 && mTitles != null) {
            return mTitles[position % mTitles.length];
        } else {
            return "";
        }
    }
}
