package com.example.nutz.dimdamlalwaniromero_midtermexam.Cart;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class AddToCart {

    private String id, name;
    private Double price, itemPrice;
    private Integer qty;

    public AddToCart(){

    }

    public AddToCart(String id, String name, double price, int qty, double itemPrice) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.itemPrice = itemPrice;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    public Double getItemPrice() { return itemPrice; }
}