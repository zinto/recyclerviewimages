package com.anhtuan89.recyclerviewimages.mRecycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.anhtuan89.recyclerviewimages.R;
/**
 * Created by PhamHuong on 8/17/2016.
 */
public class MyHolder extends RecyclerView.ViewHolder {
    TextView nameTxt;
    ImageView img;
    public MyHolder(View itemView) {
        super(itemView);
        nameTxt= (TextView) itemView.findViewById(R.id.nameTxt);
        img= (ImageView) itemView.findViewById(R.id.movieImage);
    }
}