package com.example.gary.newstext.bean;

import android.support.v4.app.Fragment;

import com.example.gary.newstext.utils.PixUtil;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;

/**
 * Created by Gary on 2017/9/24.
 */

public class BeanFragment extends Fragment {
    public void setRecyclerDecoration(EasyRecyclerView rc){
        SpaceDecoration sd = new SpaceDecoration(PixUtil.converDp2Pix(getContext(),8));
        sd.setPaddingEdgeSide(true);
        sd.setPaddingStart(true);
        sd.setPaddingHeaderFooter(false);
        rc.addItemDecoration(sd);
    }
}
