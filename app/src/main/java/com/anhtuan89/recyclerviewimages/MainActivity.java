package com.anhtuan89.recyclerviewimages;

import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.anhtuan89.recyclerviewimages.mFireBase.FireBaseClient;
import com.anhtuan89.recyclerviewimages.R;

public class MainActivity extends AppCompatActivity {

    final static String DB_URL="https://recyclerviewimages.firebaseio.com/";
    EditText nameEditText,urlEditText;
    Button saveBtn;
    RecyclerView rv;
    FireBaseClient fireBaseClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        rv= (RecyclerView) findViewById(R.id.mRecylcerID);
        rv.setLayoutManager(new LinearLayoutManager(this));
        fireBaseClient=new FireBaseClient(this,DB_URL,rv);
        fireBaseClient.refreshData();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDialog();
            }
        });
    }
    //SHOW INPUT DIALOG
    private void displayDialog()
    {
        Dialog d=new Dialog(this);
        d.setTitle("Save Online");
        d.setContentView(R.layout.custom_dialog);
        nameEditText= (EditText) d.findViewById(R.id.nameEditText);
        urlEditText= (EditText) d.findViewById(R.id.urlEditText);
        saveBtn= (Button) d.findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fireBaseClient.saveOnline(nameEditText.getText().toString(),urlEditText.getText().toString());
                nameEditText.setText("");
                urlEditText.setText("");
            }
        });
        //SHOW
        d.show();
    }
}