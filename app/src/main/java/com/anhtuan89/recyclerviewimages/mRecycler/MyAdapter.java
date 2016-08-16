package com.anhtuan89.recyclerviewimages.mRecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anhtuan89.recyclerviewimages.mPicasso.PicassoClient;
import com.anhtuan89.recyclerviewimages.model.Movie;
import com.anhtuan89.recyclerviewimages.R;

import java.util.ArrayList;

/**
 * Created by PhamHuong on 8/17/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
    Context c;
    ArrayList<Movie> movies;
    public MyAdapter(Context c, ArrayList<Movie> movies) {
        this.c = c;
        this.movies = movies;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        MyHolder holder=new MyHolder(v);
        return holder;
    }
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.nameTxt.setText(movies.get(position).getName());
        PicassoClient.downloadImage(c,movies.get(position).getUrl(),holder.img);
    }
    @Override
    public int getItemCount() {
        return movies.size();
    }
}