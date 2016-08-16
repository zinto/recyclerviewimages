package com.anhtuan89.recyclerviewimages.mFireBase;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.anhtuan89.recyclerviewimages.mRecycler.MyAdapter;
import com.anhtuan89.recyclerviewimages.model.Movie;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

/**
 * Created by PhamHuong on 8/17/2016.
 */
public class FireBaseClient {
    Context c;
    String DB_URL;
    RecyclerView rv;
    Firebase fire;
    ArrayList<Movie> movies=new ArrayList<>();
    MyAdapter adapter;
    public FireBaseClient(Context c, String DB_URL, RecyclerView rv) {
        this.c = c;
        this.DB_URL = DB_URL;
        this.rv = rv;
        //INITIALIZE
        Firebase.setAndroidContext(c);
        //INSTANTIATE
        fire=new Firebase(DB_URL);
    }
    //SAVE
    public void saveOnline(String name,String url)
    {
        Movie m=new Movie();
        m.setName(name);
        m.setUrl(url);
        fire.child("Movie").push().setValue(m);
    }
    //RETRIEVE
    public void refreshData()
    {
        fire.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }
    private void getUpdates(DataSnapshot dataSnapshot)
    {
        movies.clear();
        for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            Movie m=new Movie();
            m.setName(ds.getValue(Movie.class).getName());
            m.setUrl(ds.getValue(Movie.class).getUrl());
            movies.add(m);
        }
        if(movies.size()>0)
        {
            adapter=new MyAdapter(c,movies);
            rv.setAdapter(adapter);
        }else {
            Toast.makeText(c,"No data", Toast.LENGTH_SHORT).show();
        }
    }
}