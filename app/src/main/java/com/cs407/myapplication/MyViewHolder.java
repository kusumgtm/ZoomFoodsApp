package com.cs407.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView waterAmount, timestamp;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        waterAmount = itemView.findViewById(R.id.water_amount);
        timestamp = itemView.findViewById(R.id.time);
    }
}
