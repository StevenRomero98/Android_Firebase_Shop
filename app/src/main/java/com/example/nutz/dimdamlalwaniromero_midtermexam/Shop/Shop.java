package com.example.nutz.dimdamlalwaniromero_midtermexam.Shop;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutz.dimdamlalwaniromero_midtermexam.Admin.Admin;
import com.example.nutz.dimdamlalwaniromero_midtermexam.Admin.AdminProductList;
import com.example.nutz.dimdamlalwaniromero_midtermexam.Admin.Product;
import com.example.nutz.dimdamlalwaniromero_midtermexam.StartupScreen.Signup;
import com.example.nutz.dimdamlalwaniromero_midtermexam.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Shop extends AppCompatActivity {

    public static final String PRODUCT_ID = "com.example.nutz.dimdamlalwaniromero_midtermexam.productid";
    public static final String PRODUCT_NAME = "com.example.nutz.dimdamlalwaniromero_midtermexam.productname";

    TextView p1, p2, p3, p4, p5, p6;
    ImageButton pr1, pr2, pr3, pr4, pr5, pr6;
    EditText q1, q2, q3, q4, q5;
    CheckBox item1, item2, item3, item4, item5;

    String cart = "";
    Double totalItem = 0.00, itotal = 0.00, ftotal = 0.00;

    FirebaseAuth auth;
    FirebaseUser user;

    ListView listViewProducts;
    //a list to store all the product from firebase database
    List<Product> products;

    //our database reference object
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop);

        //list to store products
        products = new ArrayList<>();
        //getting the reference of products node
        db = FirebaseDatabase.getInstance().getReference("products");
        listViewProducts = findViewById(R.id.listViewProducts);


        //attaching listener to listview
        listViewProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product product = products.get(i);
                showProduct(product.getId(), product.getName());

            }
        });
    }

    private void showProduct(final String productID, String productName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.shop_product_dialog, null);
        dialogBuilder.setView(dialogView);


        final TextView ViewName = dialogView.findViewById(R.id.ViewName);
        ViewName.setText(productName);

        final EditText editName = dialogView.findViewById(R.id.editPrice);
        final EditText editDesc = dialogView.findViewById(R.id.editDesc);
        final EditText editPrice = dialogView.findViewById(R.id.editPrice);
        final EditText editQty = dialogView.findViewById(R.id.editQty);
        final Button buttonUpdate = dialogView.findViewById(R.id.updateProduct);
        final Button buttonDelete = dialogView.findViewById(R.id.deleteProduct);

        dialogBuilder.setTitle("Product Details");
        final AlertDialog b = dialogBuilder.create();
        b.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous product list
                products.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting product
                    Product product = postSnapshot.getValue(Product.class);
                    //adding product to the list
                    products.add(product);
                }

                //creating adapter
                ShopProductList productAdapter = new ShopProductList(Shop.this, products);
                //attaching adapter to the listview
                listViewProducts.setAdapter(productAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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

    public void Admin(View v){
        Intent i = new Intent(getApplicationContext(), Admin.class);
        startActivity(i);
    }
}
