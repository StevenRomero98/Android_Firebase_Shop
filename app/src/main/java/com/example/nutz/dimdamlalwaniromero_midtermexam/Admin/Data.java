package com.example.nutz.dimdamlalwaniromero_midtermexam.Admin;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Data {

    String id, name,desc;
    Double price;
    Integer qty;

    public Data(){

    }

    public Data(String id, String name, String desc, double price, int qty) {
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

/*
@IgnoreExtraProperties
public class Data {

    String id, name,desc, price, qty;

    public Data(){

    }

    public Data(String id, String name, String desc, String price, String qty) {
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

    public String getPrice() {
        return price;
    }

    public String getQty() {
        return qty;
    }
} */
