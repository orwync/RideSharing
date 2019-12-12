package com.example.ridesharing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Declaring all the required varables
    static ImageView profilePic;
    ImageView imgBack,imgMore;
    static TextView txtName,txtPlace,txtRides,txtFreeRides,txtCredits;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    static Model m;
    static ArrayList<Model> models;
    static RelativeLayout mainLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Initalising the views
        profilePic = (ImageView) findViewById(R.id.proflePic);
        txtName = (TextView) findViewById(R.id.name);
        txtPlace = (TextView) findViewById(R.id.place);
        txtRides = (TextView)findViewById(R.id.rides);
        txtFreeRides = (TextView)findViewById(R.id.freerides);
        txtCredits = (TextView) findViewById(R.id.credit);
        imgBack = (ImageView)findViewById(R.id.back);
        imgMore = (ImageView) findViewById(R.id.more);
        recyclerView=(RecyclerView)findViewById(R.id.recycleview);
        mainLayout=(RelativeLayout)findViewById(R.id.mainLayout);


        models = new ArrayList<>();

        //Starting an async tast for api data
        FetchData fetchData = new FetchData();
        fetchData.execute();


        //Setting up the recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(getApplicationContext(), getMyList());
        final Handler handler = new Handler();

        //Wating for the async tast to finish
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView.setAdapter(myAdapter);
            }
        }, 1000);


        //Exit button
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //Menu button(not implemented)
        imgMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    //Returning the Array list for recycler
    private ArrayList<Model> getMyList(){

        return models;
    }
}
