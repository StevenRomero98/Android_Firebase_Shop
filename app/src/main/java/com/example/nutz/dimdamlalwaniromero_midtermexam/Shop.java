package com.example.nutz.dimdamlalwaniromero_midtermexam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.nutz.dimdamlalwaniromero_midtermexam.AccountActivity.Signup;
import com.example.nutz.dimdamlalwaniromero_midtermexam.Products.P1;
import com.example.nutz.dimdamlalwaniromero_midtermexam.Products.P2;
import com.example.nutz.dimdamlalwaniromero_midtermexam.Products.P3;
import com.example.nutz.dimdamlalwaniromero_midtermexam.Products.P4;
import com.example.nutz.dimdamlalwaniromero_midtermexam.Products.P5;
import com.example.nutz.dimdamlalwaniromero_midtermexam.Products.P6;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Shop extends AppCompatActivity {

    ImageButton p1, p2, p3, p4, p5, p6;

    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        p1 = findViewById(R.id.p1);
        p2 = findViewById(R.id.p2);
        p3 = findViewById(R.id.p3);
        p4 = findViewById(R.id.p4);
        p5 = findViewById(R.id.p5);
        p6 = findViewById(R.id.p6);

    }

    public void Cart(View v){
        Intent i = new Intent(this, Cart.class);
        startActivity(i);
    }

    public void SignOut(View v){
        auth.signOut();
        finish();
        Intent i = new Intent(this, Signup.class);
        startActivity(i);
    }

   public void OpenP1(View v){
        Intent i = new Intent(getApplicationContext(), P1.class);
        startActivity(i);
    }

    public void OpenP2(View v){
        Intent i = new Intent(getApplicationContext(), P2.class);
        startActivity(i);
    }

    public void OpenP3(View v){
        Intent i = new Intent(getApplicationContext(), P3.class);
        startActivity(i);
    }

    public void OpenP4(View v){
        Intent i = new Intent(getApplicationContext(), P4.class);
        startActivity(i);
    }

    public void OpenP5(View v){
        Intent i = new Intent(getApplicationContext(), P5.class);
        startActivity(i);
    }

    public void OpenP6(View v){
        Intent i = new Intent(getApplicationContext(), P6.class);
        startActivity(i);
    }
}
