package com.example.gary.newsdemo.utils;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.provider.Settings;

/**
 * Created by Gary on 2017/9/12.
 */

public class NetWorkUtil {

    public static boolean checkNetWorkState(Context context){
        boolean flag = false;
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager.getActiveNetworkInfo() != null){
            flag = manager.getActiveNetworkInfo().isAvailable();
        }
        if (!flag){
            intoSetting(context);
        }
        return flag;
    }


    /**
     * 跳转到网络设置页面
     * @param context
     */
    public static void intoSetting(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("网络错误提示");
        builder.setMessage("你当前的网络有问题，继续请前往设置");
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = null;
                if (Build.VERSION.SDK_INT > 10){
                    intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                }else {
                    intent = new Intent();
                    ComponentName  cn= new ComponentName(
                            "com.android.settings",
                            "com.android.settings.WirelessSettings");
                    intent.setComponent(cn);
                }
                context.startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create();
        builder.show();

    }
}
