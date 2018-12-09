package com.example.nutz.dimdamlalwaniromero_midtermexam.Cart;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutz.dimdamlalwaniromero_midtermexam.Admin.Product;
import com.example.nutz.dimdamlalwaniromero_midtermexam.R;
import com.example.nutz.dimdamlalwaniromero_midtermexam.Shop.Shop;
import com.example.nutz.dimdamlalwaniromero_midtermexam.Shop.ShopProductList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {

    Double totalprice = 0.0;
    TextView TotalPrice;
    Button Checkout;

    ListView listViewProducts;
    //a list to store all the product from firebase database
    List<AddToCart> products;

    //our database reference object
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        TotalPrice = findViewById(R.id.TotalPrice);
        Checkout = findViewById(R.id.Checkout);

        //list to store products
        products = new ArrayList<>();
        //getting the reference of products node
        db = FirebaseDatabase.getInstance().getReference("mycart");
        listViewProducts = findViewById(R.id.listViewProducts);

        //attaching listener to listview
        listViewProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AddToCart add = products.get(i);
                updateOrder(add.getId(), add.getName(), String.valueOf(add.getPrice()), String.valueOf(add.getQty()), String.valueOf(add.getItemPrice()));

            }
        });
    }

    private void updateOrder(final String id, String productName, String productPrice, final String productQty, String itemprice) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.cart_update_dialog, null);
        dialogBuilder.setView(dialogView);


        final TextView ViewName = dialogView.findViewById(R.id.ViewName);
        final TextView ViewPrice = dialogView.findViewById(R.id.ViewPrice);
        final TextView ViewQty = dialogView.findViewById(R.id.ViewQty);
        final TextView ViewItemPrice = dialogView.findViewById(R.id.ViewItemPrice);

        final EditText OrderQty = dialogView.findViewById(R.id.OrderQty);

        ViewName.setText(productName);
        ViewPrice.setText(productPrice);
        ViewQty.setText(productQty);
        ViewItemPrice.setText(itemprice);

        final Button updateItem = dialogView.findViewById(R.id.updateItem);
        final Button deleteItem = dialogView.findViewById(R.id.deleteItem);

        dialogBuilder.setTitle("Product Details");
        final AlertDialog b = dialogBuilder.create();
        b.show();

        updateItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ViewName.getText().toString().trim();
                String price = ViewPrice.getText().toString().trim();
                String qty = OrderQty.getText().toString().trim();
                final double itemprice = Double.parseDouble(price) * Double.parseDouble(qty);
                if (!TextUtils.isEmpty(name)) {
                    updateItem(id, name, Double.parseDouble(price), Integer.parseInt(qty), itemprice);
                    b.dismiss();
                }
            }
        });


        deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteItem(id);
                b.dismiss();
            }
        });
    }

    private boolean updateItem(String id, String name, double price, int qty, double itemprice) {
        //getting the specified product reference
        DatabaseReference cart = FirebaseDatabase.getInstance().getReference("mycart").child(id);

        //updating product
        AddToCart add = new AddToCart(id, name, price, qty, itemprice);
        cart.setValue(add);
        Toast.makeText(getApplicationContext(), "Product Updated", Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean deleteItem(String id) {
        //getting the specified product reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("mycart").child(id);

        //removing product
        dR.removeValue();

        Toast.makeText(getApplicationContext(), "Item removed from cart", Toast.LENGTH_LONG).show();

        return true;
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
                    AddToCart product = postSnapshot.getValue(AddToCart.class);
                    //adding product to the list
                    products.add(product);

                    final double itemprice = product.getPrice() * product.getQty();
                    totalprice = totalprice + itemprice;
                    TotalPrice.setText(String.valueOf(totalprice));
                }

                //creating adapter
                CartProductList productAdapter = new CartProductList(Cart.this, products);
                //attaching adapter to the listview
                listViewProducts.setAdapter(productAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void Checkout (View v) {
        FirebaseDatabase.getInstance().getReference("mycart").removeValue();
        TotalPrice.setText(0);
    }

}
