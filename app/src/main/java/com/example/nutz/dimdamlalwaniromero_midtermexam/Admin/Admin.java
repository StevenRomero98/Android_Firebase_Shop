package com.example.nutz.dimdamlalwaniromero_midtermexam.Admin;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nutz.dimdamlalwaniromero_midtermexam.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Admin extends AppCompatActivity {
    public static final String PRODUCT_ID = "com.example.nutz.dimdamlalwaniromero_midtermexam.productid";
    public static final String PRODUCT_NAME = "com.example.nutz.dimdamlalwaniromero_midtermexam.productname";
    private static final int PICK_IMAGE_REQUEST = 1;

    EditText editTextName, editTextDesc, editTextPrice, editTextQty;
    ListView listViewProducts;

    EditText addName, addDesc, addPrice, addQty, addImage;
    Button chooseImage, add;
    ImageView testImage;
    ProgressBar progressBar;

    Uri uri;

    String name, desc, price, qty;

    //a list to store all the product from firebase database
    List<Product> products;


    //our database & storage reference object
    DatabaseReference db;
    StorageReference img;

    StorageTask imgUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);

        //getting the reference of products node
        db = FirebaseDatabase.getInstance().getReference("products");
        img = FirebaseStorage.getInstance().getReference("imgs");

        //getting views

        addName = findViewById(R.id.addname);
        addDesc = findViewById(R.id.adddesc);
        addPrice = findViewById(R.id.addprice);
        addQty = findViewById(R.id.addqty);
        addImage = findViewById(R.id.addImage);
        testImage = findViewById(R.id.testImage);
        progressBar = findViewById(R.id.progressBar);

        chooseImage = findViewById(R.id.chooseImage);
        add = findViewById(R.id.add);


        editTextName = (EditText) findViewById(R.id.editName);
        editTextDesc = (EditText) findViewById(R.id.editDesc);
        editTextPrice = (EditText) findViewById(R.id.editPrice);
        editTextQty = (EditText) findViewById(R.id.editQty);

        listViewProducts = (ListView) findViewById(R.id.listViewProducts);

        //list to store products
        products = new ArrayList<>();

        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgUpload != null && imgUpload.isInProgress()) {
                    Toast.makeText(Admin.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    addProduct();
                }
            }
        });

        //attaching listener to listview
        listViewProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product product = products.get(i);
                showUpdateDeleteDialog(product.getId(), product.getName(), product.getDesc(), String.valueOf(product.getPrice()), String.valueOf(product.getQty()), product.getImgName(), product.getImgUrl());

            }
        });
    }

    private void showUpdateDeleteDialog(final String productID, String productName, String productDesc, String productPrice, String productQty, String imgName, String imgUrl) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.admin_update_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText editName = dialogView.findViewById(R.id.editName);
        final EditText editDesc = dialogView.findViewById(R.id.editDesc);
        final EditText editPrice = dialogView.findViewById(R.id.editPrice);
        final EditText editQty = dialogView.findViewById(R.id.editQty);
        final EditText editImageName = dialogView.findViewById(R.id.editImageName);
        final EditText editImage = dialogView.findViewById(R.id.editImage);
        final ProgressBar progressBar = dialogView.findViewById(R.id.progressBar);
        final Button buttonUpdate = dialogView.findViewById(R.id.updateProduct);
        final Button buttonDelete = dialogView.findViewById(R.id.deleteProduct);

        editName.setText(productName);
        editDesc.setText(productDesc);
        editPrice.setText(productPrice);
        editQty.setText(productQty);

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
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("products").child(id);

        //updating product
        //Product product = new Product(id, name, desc, price, qty);
        //db.setValue(product);
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
    public void addProduct() {
        //getting the values to save
        final String name = addName.getText().toString().trim();
        final String desc = addDesc.getText().toString().trim();
        final String price = addPrice.getText().toString().trim();
        final String qty = addQty.getText().toString().trim();
        final String imgName = addImage.getText().toString().trim();


        //checking if the value is provided
        if (uri != null && !TextUtils.isEmpty(name) && !TextUtils.isEmpty(desc) && !TextUtils.isEmpty(price) && !TextUtils.isEmpty(qty)) {

            StorageReference fileReference = img.child(System.currentTimeMillis()
                    + "." + getFileExtension(uri));

            imgUpload = fileReference.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(0);
                                }
                            }, 500);

                            Toast.makeText(Admin.this, "Upload successful", Toast.LENGTH_LONG).show();

                            String id = db.push().getKey();
                            Product product = new Product(id, name, desc, Double.parseDouble(price), Integer.parseInt(qty), imgName, taskSnapshot.getUploadSessionUri().toString());
                            //String uploadId = db.push().getKey();

                            db.child(id).setValue(product);
                            //db.child(uploadId).setValue(product);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Admin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progressBar.setProgress((int) progress);
                        }
                    });

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Product
            String id = db.push().getKey();

            //creating an Product Object
            //Product product = new Product(id, name, desc, Double.parseDouble(price), Integer.parseInt(qty));

            //Saving the Product
            //db.child(id).setValue(product);

            //setting fields to blank again
            addName.setText("");
            addDesc.setText("");
            addPrice.setText("");
            addQty.setText("");

            //displaying a success toast
            Toast.makeText(this, "Product added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please fill out empty fields", Toast.LENGTH_LONG).show();
        }
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            uri = data.getData();

            Picasso.get().load(uri).into(testImage);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
/*
    private void uploadFile() {
        if (uri != null) {
            StorageReference fileReference = img.child(System.currentTimeMillis()
                    + "." + getFileExtension(uri));

            imgUpload = fileReference.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(0);
                                }
                            }, 500);

                            Toast.makeText(Admin.this, "Upload successful", Toast.LENGTH_LONG).show();
                            Upload upload = new Upload(addImage.getText().toString().trim(),
                                    taskSnapshot.getUploadSessionUri().toString());
                            String uploadId = db.push().getKey();
                            db.child(uploadId).setValue(upload);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Admin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progressBar.setProgress((int) progress);
                        }
                    });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    } */
}
