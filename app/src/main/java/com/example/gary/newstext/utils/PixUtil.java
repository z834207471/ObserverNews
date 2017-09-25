package com.example.gary.newstext.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Gary on 2017/9/24.
 */

public class PixUtil {
    public static int converDp2Pix(Context context,int dpValue){
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int px = dpValue * (dm.densityDpi/160);
        return px;
    }
}
