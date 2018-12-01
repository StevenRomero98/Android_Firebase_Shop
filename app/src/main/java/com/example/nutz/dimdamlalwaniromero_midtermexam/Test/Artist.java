package com.example.nutz.dimdamlalwaniromero_midtermexam.Test;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Belal on 2/26/2017.
 */
@IgnoreExtraProperties
public class Artist {

    String id, name,desc;
    Double price;
    Integer qty;

    public Artist(){

    }

    public Artist(String id, String name, String desc, double price, int qty) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.qty = qty;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public double getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }
}