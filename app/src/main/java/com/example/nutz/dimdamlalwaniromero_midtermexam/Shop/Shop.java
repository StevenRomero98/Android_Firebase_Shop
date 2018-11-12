package com.example.nutz.dimdamlalwaniromero_midtermexam.Shop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutz.dimdamlalwaniromero_midtermexam.Admin.AddData;
import com.example.nutz.dimdamlalwaniromero_midtermexam.StartupScreen.Signup;
import com.example.nutz.dimdamlalwaniromero_midtermexam.Products.P1;
import com.example.nutz.dimdamlalwaniromero_midtermexam.Products.P2;
import com.example.nutz.dimdamlalwaniromero_midtermexam.Products.P3;
import com.example.nutz.dimdamlalwaniromero_midtermexam.Products.P4;
import com.example.nutz.dimdamlalwaniromero_midtermexam.Products.P5;
import com.example.nutz.dimdamlalwaniromero_midtermexam.Products.P6;
import com.example.nutz.dimdamlalwaniromero_midtermexam.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Shop extends AppCompatActivity {

    TextView p1, p2, p3, p4, p5, p6;
    ImageButton pr1, pr2, pr3, pr4, pr5, pr6;
    EditText q1, q2, q3, q4, q5;
    CheckBox item1, item2, item3, item4, item5;

    String cart = "";
    Double totalItem = 0.00, itotal = 0.00, ftotal = 0.00;

    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        p1 = findViewById(R.id.p1);
        p2 = findViewById(R.id.p2);
        p3 = findViewById(R.id.p3);
        p4 = findViewById(R.id.p4);
        p5 = findViewById(R.id.p5);

        pr1 = findViewById(R.id.pr1);
        pr2 = findViewById(R.id.pr2);
        pr3 = findViewById(R.id.pr3);
        pr4 = findViewById(R.id.pr4);
        pr5 = findViewById(R.id.pr5);

        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);
        item4 = findViewById(R.id.item4);
        item5 = findViewById(R.id.item5);

        q1 = findViewById(R.id.q1);
        q2 = findViewById(R.id.q2);
        q3 = findViewById(R.id.q3);
        q4 = findViewById(R.id.q4);
        q5 = findViewById(R.id.q5);

    }

    public void SignOut(View v){
        auth.signOut();
        finish();
        Intent i = new Intent(this, Signup.class);
        startActivity(i);
    }


    public void Compute(View v) {
        if (item1.isChecked()) {
            totalItem = Double.parseDouble(p1.getText().toString()) * Integer.parseInt(q1.getText().toString());
            cart = "x" + q1.getText().toString() + "    " + item1.getText().toString() + "   $" + totalItem + "\n";
            itotal = totalItem;

            if (q1.toString() == "") {
                Toast.makeText(this, "Invalid quantity of selected item!", Toast.LENGTH_SHORT).show();
            }
        }
        if (item2.isChecked()) {
            totalItem = Double.parseDouble(p2.getText().toString()) * Integer.parseInt(q2.getText().toString());
            cart = cart + "x" + q2.getText().toString() + "    " + item2.getText().toString() + "   $" + totalItem + "\n";
            itotal = itotal + totalItem;

            if (q2.toString() == "") {
                Toast.makeText(this, "Invalid quantity of selected item!", Toast.LENGTH_SHORT).show();
            }
        }

        if (item3.isChecked()) {
            totalItem = Double.parseDouble(p3.getText().toString()) * Integer.parseInt(q3.getText().toString());
            cart = cart + "x" + q3.getText().toString() + "    " + item3.getText().toString() + "   $" + totalItem + "\n";
            itotal = itotal + totalItem;

            if (q3.toString() == "") {
                Toast.makeText(this, "Invalid quantity of selected item!", Toast.LENGTH_SHORT).show();
            }
        }
        if (item4.isChecked()) {
            totalItem = Double.parseDouble(p4.getText().toString()) * Integer.parseInt(q4.getText().toString());
            cart = cart + "x" + q4.getText().toString() + "    " + item4.getText().toString() + "   $" + totalItem + "\n";
            itotal = itotal + totalItem;

            if (q3.toString() == "") {
                Toast.makeText(this, "Invalid quantity of selected item!", Toast.LENGTH_SHORT).show();
            }
        }
        if (item5.isChecked()) {
            totalItem = Double.parseDouble(p5.getText().toString()) * Integer.parseInt(q5.getText().toString());
            cart = cart + "x" + q5.getText().toString() + "    " + item5.getText().toString() + "   $" + totalItem + "\n";
            itotal = itotal + totalItem;

            if (q3.toString() == "") {
                Toast.makeText(this, "Invalid quantity of selected item!", Toast.LENGTH_SHORT).show();
            }
        }

        if (itotal <= 0.00) {
            Toast.makeText(this, "Please check the quantity of your selected items!", Toast.LENGTH_SHORT).show();
        }

        if (item1.isChecked() && q1.toString() == "0" || item2.isChecked() && q2.toString() == "0" || item2.isChecked() && q3.toString() == "0") {
            Toast.makeText(this, "No quantity set on selected Item", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "" + itotal, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Cart.class);
            intent.putExtra("myCart", cart);
            intent.putExtra("myTotal", itotal.toString());
            startActivity(intent);

        }
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

    public void Admin(View v){
        Intent i = new Intent(getApplicationContext(), AddData.class);
        startActivity(i);
    }
}
