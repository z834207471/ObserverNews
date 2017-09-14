package com.example.gary.newsdemo.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.gary.newsdemo.R;
import com.example.gary.newsdemo.ui.meinv.PictureActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * Created by Gary on 2017/9/12.
 */

public class PictureUtil{

    public static void showPic(Context context, String picUrl, ImageView img){
        Glide.with(context).
                load(picUrl).
                centerCrop().
                placeholder(R.mipmap.default_pic).
                into(img);
    }

    public static void savePic(ImageView image, Context context) {
        File directory =new File(Environment.getExternalStorageDirectory(),"grPicture");
        if (!directory.exists()){
            directory.mkdir();
        }
        Bitmap drawingCache =image.getDrawingCache();
        try{
            File file = new File(directory,new Date().getTime() + ".jpg");
            FileOutputStream fos = new FileOutputStream(file);
            drawingCache.compress(Bitmap.CompressFormat.JPEG,100,fos);
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(file);
            intent.setData(uri);
            context.sendBroadcast(intent);
            Toast.makeText(context,
                    "保存成功", Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(context,
                    "阿偶出错了呢", Toast.LENGTH_LONG).show();
        }
    }
}
