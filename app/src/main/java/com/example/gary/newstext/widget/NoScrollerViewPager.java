package com.example.gary.newstext.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Gary on 2017/9/24.
 */

public class NoScrollerViewPager extends ViewPager{
    public NoScrollerViewPager(Context context) {
        super(context);
    }

    public NoScrollerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
