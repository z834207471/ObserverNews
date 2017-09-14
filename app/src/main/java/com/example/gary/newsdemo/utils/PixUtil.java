package com.example.gary.newsdemo.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by Gary on 2017/9/12.
 */

public class PixUtil {
    public static int convertDp2Px(int dp,Context context){
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        int px = (int)(dp * (dm.densityDpi / 160));
        return px;
    }
}
