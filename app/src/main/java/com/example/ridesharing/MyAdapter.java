/*===========================================
Title:Ride Sharing App
Author:Orwyn Carvalho
Date:12/12/2019
=============================================*/




package com.example.ridesharing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


//Adapter class for recycler view
public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
    Context c;
    ArrayList<Model> models;

    public MyAdapter(Context c,ArrayList<Model> models){
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,null);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.from.setText(models.get(position).getFrom());
        holder.fromDate.setText(models.get(position).getFromDate());
        holder.to.setText(models.get(position).getTo());
        holder.toDate.setText(models.get(position).getToDate());
        holder.price.setText(models.get(position).getPrice());
        holder.time.setText(models.get(position).getTime());
        holder.currency.setText(models.get(position).getCurrency());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
