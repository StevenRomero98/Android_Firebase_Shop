package com.example.nutz.dimdamlalwaniromero_midtermexam.Admin;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nutz.dimdamlalwaniromero_midtermexam.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Admin extends AppCompatActivity {
    public static final String PRODUCT_ID = "com.example.nutz.dimdamlalwaniromero_midtermexam.productid";
    public static final String PRODUCT_NAME = "com.example.nutz.dimdamlalwaniromero_midtermexam.productname";

    EditText editTextName, editTextDesc, editTextPrice, editTextQty;
    ListView listViewProducts;

    EditText addName, addDesc, addPrice, addQty;
    Button add;

    String name, desc, price, qty;

    //a list to store all the product from firebase database
    List<Product> products;

    //our database reference object
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);

        //getting the reference of products node
        db = FirebaseDatabase.getInstance().getReference("products");

        //getting views

        addName = findViewById(R.id.addname);
        addDesc = findViewById(R.id.adddesc);
        addPrice = findViewById(R.id.addprice);
        addQty = findViewById(R.id.addqty);

        add = findViewById(R.id.add);


        editTextName = (EditText) findViewById(R.id.editName);
        editTextDesc = (EditText) findViewById(R.id.editDesc);
        editTextPrice = (EditText) findViewById(R.id.editPrice);
        editTextQty = (EditText) findViewById(R.id.editQty);

        listViewProducts = (ListView) findViewById(R.id.listViewProducts);

        //list to store products
        products = new ArrayList<>();

        //attaching listener to listview
        listViewProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product product = products.get(i);
                showUpdateDeleteDialog(product.getId(), product.getName());

            }
        });
    }

    private void showUpdateDeleteDialog(final String productID, String productName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.admin_update_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText editName = dialogView.findViewById(R.id.editPrice);
        final EditText editDesc = dialogView.findViewById(R.id.editDesc);
        final EditText editPrice = dialogView.findViewById(R.id.editPrice);
        final EditText editQty = dialogView.findViewById(R.id.editQty);
        final Button buttonUpdate = dialogView.findViewById(R.id.updateProduct);
        final Button buttonDelete = dialogView.findViewById(R.id.deleteProduct);

        dialogBuilder.setTitle(productName);
        final AlertDialog b = dialogBuilder.create();
        b.show();


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editName.getText().toString().trim();
                String desc = editDesc.getText().toString().trim();
                String price = editPrice.getText().toString().trim();
                String qty = editQty.getText().toString().trim();
                if (!TextUtils.isEmpty(name)) {
                    updateProduct(productID, name, desc, Double.parseDouble(price), Integer.parseInt(qty));
                    b.dismiss();
                }
            }
        });


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteProduct(productID);
                b.dismiss();
            }
        });
    }

    private boolean updateProduct(String id, String name, String desc, double price, int qty) {
        //getting the specified product reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("products").child(id);

        //updating product
        Product product = new Product(id, name, desc, price, qty);
        dR.setValue(product);
        Toast.makeText(getApplicationContext(), "Product Updated", Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean deleteProduct(String id) {
        //getting the specified product reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("products").child(id);

        //removing product
        dR.removeValue();

        //getting the tracks reference for the specified product
        DatabaseReference drTracks = FirebaseDatabase.getInstance().getReference("tracks").child(id);

        //removing all tracks
        drTracks.removeValue();
        Toast.makeText(getApplicationContext(), "Product Deleted", Toast.LENGTH_LONG).show();

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
                    Product product = postSnapshot.getValue(Product.class);
                    //adding product to the list
                    products.add(product);
                }

                //creating adapter
                AdminProductList productAdapter = new AdminProductList(Admin.this, products);
                //attaching adapter to the listview
                listViewProducts.setAdapter(productAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    /*
    * This method is saving a new product to the
    * Firebase Realtime Database
    * */
    public void addProduct(View v) {
        //getting the values to save
        String name = addName.getText().toString().trim();
        String desc = addDesc.getText().toString().trim();
        String price = addPrice.getText().toString().trim();
        String qty = addQty.getText().toString().trim();

        //checking if the value is provided
        if (!TextUtils.isEmpty(name)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Product
            String id = db.push().getKey();

            //creating an Product Object
            Product product = new Product(id, name, desc, Double.parseDouble(price), Integer.parseInt(qty));

            //Saving the Product
            db.child(id).setValue(product);

            //setting fields to blank again
            addName.setText("");

            //displaying a success toast
            Toast.makeText(this, "Product added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }
}
