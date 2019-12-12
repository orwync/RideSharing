package com.example.ridesharing;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FetchData extends AsyncTask<Void,Void,Void> {
    //Inintalization
    String out="";
    String firstName,lastName,city,country,image_url,rides,freeRides,value,currency_symbol;
    JSONArray trips;
    ArrayList<Model> models;
    Model m;
    String darkColor,lightColor;
    //String from,fromDate,to,toDate,price,duration;

    //Background task
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            //Getting the API data in JSON form
            URL data = new URL("https://gist.githubusercontent.com/iranjith4/522d5b466560e91b8ebab54743f2d0fc/raw/7b108e4aaac287c6c3fdf93c3343dd1c62d24faf/radius-mobile-intern.json");
            HttpURLConnection httpURLConnection = (HttpURLConnection) data.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            while(line!=null){
                line = bufferedReader.readLine();
                out = out+line;
            }

            //Taking the data from JSON
            JSONObject JSONout = new JSONObject(out);

            JSONObject details = JSONout.getJSONObject("data");
            JSONObject profile = details.getJSONObject("profile");
            JSONObject stats = details.getJSONObject("stats");
            JSONObject credits = stats.getJSONObject("credits");
            JSONObject color = details.getJSONObject("theme");

            darkColor = color.getString("dark_colour");

            trips = details.getJSONArray("trips");

            firstName = profile.getString("first_name");
            lastName = profile.getString("last_name");
            image_url = profile.getString("middle_name");
            city = profile.getString("city");
            country = profile.getString("Country");

            rides = stats.getString("rides");
            freeRides = stats.getString("free_rides");

            value = credits.getString("value");
            currency_symbol = credits.getString("currency_symbol");




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    //Post async task
    @Override
    protected void onPostExecute(Void aVoid) {
        models=new ArrayList<>();
        super.onPostExecute(aVoid);

        //formatting the data

        String name = firstName+" "+lastName;
        String place = city+","+country;
        String total = currency_symbol+value;


        //Setting the primary color(Secondary color hard coded)
        MainActivity.mainLayout.setBackgroundColor(Color.parseColor(darkColor));

        //Setting the profile data
        MainActivity.txtName.setText(name);
        MainActivity.txtPlace.setText(place);
        MainActivity.txtRides.setText(rides);
        MainActivity.txtFreeRides.setText(freeRides);
        MainActivity.txtCredits.setText(total);
        Picasso.get().load(image_url).into(MainActivity.profilePic);


        //Setting the trip data
        for(int i=0;i<trips.length();i++){
            try {
                JSONObject tripDetails= trips.getJSONObject(i);
                MainActivity.m = new Model();
                MainActivity.m.setFrom(tripDetails.getString("from"));
                MainActivity.m.setFromDate(epochToTime(tripDetails.getString("from_time")));
                MainActivity.m.setTo(tripDetails.getString("to"));
                MainActivity.m.setToDate(epochToTime(tripDetails.getString("to_time")));

                JSONObject cost=tripDetails.getJSONObject("cost");

                MainActivity.m.setPrice(cost.getString("value"));
                MainActivity.m.setCurrency(cost.getString("currency_symbol"));
                MainActivity.m.setTime(minutesToHours(tripDetails.getString("trip_duration_in_mins")));
                MainActivity.models.add(MainActivity.m);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }


    //Minutes to proper format converter
    private String minutesToHours(String min){
        int time= Integer.parseInt(min);
        int hours=time/60;
        int minutes=time%60;

        return "Travel time: "+String.valueOf(hours)+"h "+String.valueOf(minutes)+"min";
    }


    //EPOC to proper format converter
    private String epochToTime(String epoch){
        // convert seconds to milliseconds
        Date date = new java.util.Date(Long.parseLong(epoch)*1000L);
         // the format of your date
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMM dd, HH:mm");
        // give a timezone reference for formatting (see comment at the bottom)
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-4"));
        String formattedDate = sdf.format(date);
        return formattedDate;
    }
}
