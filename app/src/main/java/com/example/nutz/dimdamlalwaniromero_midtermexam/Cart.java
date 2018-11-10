package com.example.nutz.dimdamlalwaniromero_midtermexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Cart extends AppCompatActivity {

    TextView cart;
    String finalCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cart = findViewById(R.id.cart);

        finalCart = getIntent().getStringExtra("myCart");

        cart.setText(finalCart);
    }
}
