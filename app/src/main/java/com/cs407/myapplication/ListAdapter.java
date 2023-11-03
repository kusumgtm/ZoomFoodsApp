package com.cs407.myapplication;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter  {
   String time_stamp;
   String amount;

   String title;


    public ListAdapter(String time_stamp, String amount, String title) {
        this.time_stamp = time_stamp;
        this.amount = amount;
        this.title = title;
    }

    public String getTime_stamp() {
        return time_stamp;
    }

    public String getAmount() {
        return amount;
    }

    public String getTitle(){
        return title;
    }
}
