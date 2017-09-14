package com.example.gary.newsdemo.ui.meinv.adapter;


import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.example.gary.newsdemo.R;
import com.example.gary.newsdemo.bean.MeinvBean;
import com.example.gary.newsdemo.bean.NewsBean;
import com.example.gary.newsdemo.utils.PictureUtil;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Gary on 2017/9/12.
 */

public class MeinvViewHolder extends BaseViewHolder<MeinvBean.MeinvListBean> {
    ImageView iv;

    public MeinvViewHolder(ViewGroup parent) {
        super(new ImageView(parent.getContext()));
        iv = (ImageView) itemView;
        iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    @Override
    public void setData(MeinvBean.MeinvListBean data) {
        final DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        Glide.with(getContext())
                .load(data.getPicUrl())
                .asBitmap()
                .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL) {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                        int width = dm.widthPixels / 3 - 6;
                        int height = bitmap.getHeight() * width / bitmap.getWidth();
                        ViewGroup.LayoutParams lp = iv.getLayoutParams();
                        lp.width = width;
                        lp.height = height;
                        iv.setLayoutParams(lp);
                        iv.setImageBitmap(bitmap
                        );

                    }
                });
    }
}
