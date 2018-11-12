package com.example.nutz.dimdamlalwaniromero_midtermexam.Shop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.nutz.dimdamlalwaniromero_midtermexam.R;

public class Cart extends AppCompatActivity {

    TextView cart, cartTotal, saved, discount, total;
    String finalCart, finalTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cart = findViewById(R.id.cart);
        total = findViewById(R.id.total);

        finalCart = getIntent().getStringExtra("myCart");
        finalTotal = getIntent().getStringExtra("myTotal");

        cart.setText(finalCart);
        total.setText(finalTotal);

    }
}