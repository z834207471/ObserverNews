package com.example.gary.newstext.ui.meinv.adapter;

import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.example.gary.newstext.gson.MeinvBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Gary on 2017/9/24.
 */

public class MeinvViewHold extends BaseViewHolder<MeinvBean.MeinvListBean>{
    private ImageView mImg;

    public MeinvViewHold(ViewGroup parent) {
        super(new ImageView(parent.getContext()));
        mImg = (ImageView) itemView;
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mImg.setLayoutParams(lp);
        mImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
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
                        ViewGroup.LayoutParams lp = mImg.getLayoutParams();
                        lp.height = height;
                        lp.width = width;
                        mImg.setLayoutParams(lp);
                        mImg.setImageBitmap(bitmap);


                    }
                });
    }
}
