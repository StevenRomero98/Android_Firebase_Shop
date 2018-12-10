package com.example.nutz.dimdamlalwaniromero_midtermexam.Admin;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Product {

    private String id, name,desc;
    private Double price;
    private Integer qty;

    private String imgName;
    private String imgUrl;

    public Product(){

    }

    public Product(String id, String name, String desc, double price, int qty, String imgName, String imgUrl) {

        if (name.trim().equals("")) {
            name = "No Name";
        }

        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.qty = qty;

        this.imgName = imgName;
        this.imgUrl = imgUrl;
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

    public String getImgName() { return imgName; }

    public String getImgUrl() { return imgUrl; }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}