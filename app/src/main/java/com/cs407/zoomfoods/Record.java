package com.cs407.zoomfoods;

public class Record  {
    String time_stamp;
    String amount;

    String title;
    Integer id;


    public Record(Integer id, String time_stamp, String amount, String title) {
        this.time_stamp = time_stamp;
        this.amount = amount;
        this.title = title;
        this.id = id;
    }

    public String getTime() {
        return time_stamp;
    }

    public String getAmount() {
        return amount;
    }

    public String getTitle(){
        return title;
    }

    public Integer getId() {return id; }
}
