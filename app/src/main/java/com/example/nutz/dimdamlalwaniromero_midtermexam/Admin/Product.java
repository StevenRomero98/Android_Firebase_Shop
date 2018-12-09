package com.example.nutz.dimdamlalwaniromero_midtermexam.Admin;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Product {

    private String id, name,desc;
    private Double price;
    private Integer qty;

    public Product(){

    }

    public Product(String id, String name, String desc, double price, int qty) {
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