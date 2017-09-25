package com.example.gary.newstext.ui.meinv;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.example.gary.newstext.R;
import com.example.gary.newstext.bean.BeanActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.GET;

/**
 * Created by Gary on 2017/9/24.
 */

public class PicOpenActivity extends BeanActivity {
    @BindView(R.id.iv_meinv)
    ImageView mImg;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_fragmnet_meinv);
        ButterKnife.bind(this);
        final DisplayMetrics dm = getResources().getDisplayMetrics();

        Glide.with(this)
                .load(getIntent()
                        .getStringExtra("picurl"))
                .asBitmap()
                .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                          @Override
                          public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                              Log.d("bitmap", "高" + bitmap.getHeight() + "宽" + bitmap.getWidth());
                              int width = dm.widthPixels;//宽度为屏幕宽度一半
                              int height = bitmap.getHeight() * width / bitmap.getWidth();//计算View的高度
                              Log.d("picture", "高" + height + "宽" + width); //获取bitmap信息，可赋值给外部变量操作，也可在此时行操作。
                              ViewGroup.LayoutParams params = mImg.getLayoutParams();
                              params.height = height;
                              params.width = width;
                              mImg.setLayoutParams(params);
                              mImg.setScaleType(ImageView.ScaleType.FIT_XY);
                              mImg.setImageBitmap(bitmap);
                          }
                      }
                );
        mImg.setDrawingCacheEnabled(true);
        mImg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showSavePicDialog();
                return true;
            }
        });
    }

    private void showSavePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PicOpenActivity.this);
        builder.setMessage("是否保存图片")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        savePic();
                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).create().show();
    }

    private void savePic() {
        File sdfile = Environment.getExternalStorageDirectory();
        File savefile = new File(sdfile, "grpath");
        if (!savefile.exists()) {
            savefile.mkdir();
        }
        Bitmap   drawingcache = mImg.getDrawingCache();
            Log.e("grgrgr", "savePic: "+drawingcache.getWidth() );
        try {
            File file = new File(savefile, new Date().getTime() + ".jpg");
            FileOutputStream fos = new FileOutputStream(file);
            drawingcache.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(file);
            intent.setData(uri);
            sendBroadcast(intent);
            Toast.makeText(PicOpenActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(PicOpenActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
        }
    }
}
