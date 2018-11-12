package com.example.nutz.dimdamlalwaniromero_midtermexam.StartupScreen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nutz.dimdamlalwaniromero_midtermexam.R;
import com.example.nutz.dimdamlalwaniromero_midtermexam.Shop.Shop;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {

    EditText regEmail, regName, regPass, regPass2;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        regEmail = findViewById(R.id.regEmail);
        regPass = findViewById(R.id.regPass);
        regPass2 = findViewById(R.id.regPass2);

        auth = FirebaseAuth.getInstance();
    }

    public void Signup(View v){
        if(regEmail.getText().toString().equals("") && regPass.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Blank not allowed", Toast.LENGTH_LONG);
        }
        else {
            String email = regEmail.getText().toString();
            String password = regPass.getText().toString();

            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "User created successfully!", Toast.LENGTH_SHORT).show();
                                finish();
                                Intent i = new Intent(getApplicationContext(), Shop.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), "User could not be created!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }
}
