package com.example.gary.newsdemo.ui.meinv;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.example.gary.newsdemo.R;
import com.example.gary.newsdemo.base.BaseActivity;
import com.example.gary.newsdemo.utils.PictureUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gary on 2017/9/13.
 */

public class PictureActivity extends BaseActivity {
    @BindView(R.id.image)
    ImageView image;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        String picurl = bundle.getString("mnpicurl");
        String url = bundle.getString("mnurl");
        final DisplayMetrics dm = getResources().getDisplayMetrics();
        Glide.with(this).load(picurl)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL) {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                        int width = dm.widthPixels;//宽度为屏幕宽度一半
                        int height = bitmap.getHeight() * width / bitmap.getWidth();//计算
                        ViewGroup.LayoutParams lp = image.getLayoutParams();
                        lp.height = height;
                        lp.width = width;
                        image.setLayoutParams(lp);
                        image.setScaleType(ImageView.ScaleType.FIT_XY);
                        image.setImageBitmap(bitmap);
                    }
                });
        image.setDrawingCacheEnabled(true);
        image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                    new AlertDialog.Builder(PictureActivity.this)
                            .setMessage("保存图片")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            PictureUtil.savePic(image, PictureActivity.this);
                        }
                    }).create().show();
                return true;
            }
        });
    }
}
