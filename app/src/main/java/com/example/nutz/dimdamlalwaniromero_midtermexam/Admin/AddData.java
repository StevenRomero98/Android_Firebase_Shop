package com.example.nutz.dimdamlalwaniromero_midtermexam.Admin;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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

import java.util.List;

public class AddData extends AppCompatActivity {

    EditText addName, addDesc, addPrice, addQty;
    Button add;
    String name, desc, price, qty;
    ListView dataListView;

    DatabaseReference db;

    List<Data> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        db = FirebaseDatabase.getInstance().getReference("DATA");

        addName = findViewById(R.id.addname);
        addDesc = findViewById(R.id.adddesc);
        addPrice = findViewById(R.id.addprice);
        addQty = findViewById(R.id.addqty);
        dataListView = findViewById(R.id.dataListView);

        add = findViewById(R.id.add);

    }

  /*  private boolean validateInputs(String name, String desc, String price, String qty) {
        if (name.isEmpty()) {
            addName.setError("Name required");
            addName.requestFocus();
            return true;
        }

        if (desc.isEmpty()) {
            addDesc.setError("Description required");
            addDesc.requestFocus();
            return true;
        }

        if (price.isEmpty()) {
            addPrice.setError("Price required");
            addPrice.requestFocus();
            return true;
        }

        if (qty.isEmpty()) {
            addQty.setError("Quantity required");
            addQty.requestFocus();
            return true;
        }
        return false;
    } */

    public void addData(View v) {

        name = addName.getText().toString().trim();
        desc = addDesc.getText().toString().trim();
        price = addPrice.getText().toString().trim();
        qty = addQty.getText().toString().trim();

        if (!TextUtils.isEmpty(name)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            String id = db.push().getKey();

            //creating an Artist Object
            Data data = new Data(id, name,desc,Double.parseDouble(price),Integer.parseInt(qty));

            //Saving the Artist
            db.child(id).setValue(data);

            addName.setText("");
            addDesc.setText("");
            addPrice.setText("");
            addQty.setText("");

            //displaying a success toast
            Toast.makeText(this, "Product Added!", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Check the product details!", Toast.LENGTH_LONG).show();
        }
    }
/*
    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                dataList.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    Data data = postSnapshot.getValue(Data.class);
                    //adding artist to the list
                    dataList.add(data);
                }

                //creating adapter
                DataList adapter = new DataList(AddData.this, dataList);
                //attaching adapter to the listview
                //dataListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void addData(View v) {

        name = addName.getText().toString().trim();
        desc = addDesc.getText().toString().trim();
        price = addPrice.getText().toString().trim();
        qty = addQty.getText().toString().trim();

        if (!TextUtils.isEmpty(name)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            String id = db.push().getKey();

            //creating an Artist Object
            Data data = new Data(id,name,desc,price,qty);

            //Saving the Artist
            db.child(id).setValue(data);

            addName.setText("");
            addDesc.setText("");
            addPrice.setText("");
            addQty.setText("");

            //displaying a success toast
            Toast.makeText(this, "Product Added!", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Check the product details!", Toast.LENGTH_LONG).show();
        }
    } */

}
