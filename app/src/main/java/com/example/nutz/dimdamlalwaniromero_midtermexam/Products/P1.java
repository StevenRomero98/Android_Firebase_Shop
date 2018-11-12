package com.example.nutz.dimdamlalwaniromero_midtermexam.Products;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutz.dimdamlalwaniromero_midtermexam.Shop.Cart;
import com.example.nutz.dimdamlalwaniromero_midtermexam.R;

public class P1 extends AppCompatActivity {

    TextView name, price;
    EditText qty;
    Button add;

    String cart = "";

    Double totalprice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p1);

        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        qty = findViewById(R.id.qty);
        add = findViewById(R.id.add);

       /* DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6)); */
    }

    public void add(View v){
        Toast.makeText(this, "Item added to cart!", Toast.LENGTH_SHORT).show();
        totalprice = Double.parseDouble(price.getText().toString()) * Integer.parseInt(qty.getText().toString());
        cart = "x" + qty.getText().toString() + "    " + name.getText().toString() + "   $" + totalprice + "\n";

        Toast.makeText(this, "" + cart, Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, Cart.class);

        i.putExtra("myCart", cart);
        startActivity(i);

    }

}
