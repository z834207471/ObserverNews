package com.example.gary.newstext.utils;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.provider.Settings;

import java.util.Set;

/**
 * Created by Gary on 2017/9/24.
 */

public class NetWorkUtil {
    public static void onFailedRequest(Context context) {
        boolean flag = false;
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager.getActiveNetworkInfo() != null) {
            flag = manager.getActiveNetworkInfo().isAvailable();
        }
        if (flag == false) {
            intoSetting(context);
        }

    }

    private static void intoSetting(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("进入设置")
                .setMessage("您的网络没连接，请进入wife设置")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = null;
                        if (Build.VERSION.SDK_INT > 10){
                            intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                        } else {
                            intent = new Intent();
                            ComponentName cn = new ComponentName(
                                    "com.android.settings",
                                    "com.android.settings.WirelessSettings"
                            );
                            intent.setComponent(cn);
                        }
                        context.startActivity(intent);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).create().show();
    }
}
