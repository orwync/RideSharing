package com.example.ridesharing;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


//Setting the layout file for recycle view
public class MyHolder extends RecyclerView.ViewHolder {
    TextView from,fromDate,to,toDate,price,time,currency;
    public static RelativeLayout small;


    public MyHolder(@NonNull View itemView) {
        super(itemView);

        this.from=itemView.findViewById(R.id.from);
        this.fromDate=itemView.findViewById(R.id.fromDate);
        this.to=itemView.findViewById(R.id.to);
        this.toDate=itemView.findViewById(R.id.toDate);
        this.price=itemView.findViewById(R.id.price);
        this.time=itemView.findViewById(R.id.time);
        this.currency=itemView.findViewById(R.id.currency);
        this.small=itemView.findViewById(R.id.smallLayout);


    }
}
