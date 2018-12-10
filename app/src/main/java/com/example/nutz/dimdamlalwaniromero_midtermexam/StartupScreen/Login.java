package com.example.nutz.dimdamlalwaniromero_midtermexam.StartupScreen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nutz.dimdamlalwaniromero_midtermexam.Cart.Cart;
import com.example.nutz.dimdamlalwaniromero_midtermexam.R;
import com.example.nutz.dimdamlalwaniromero_midtermexam.Shop.Shop;
import com.example.nutz.dimdamlalwaniromero_midtermexam.Admin.Admin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    Button btnSignup, btnLogin;
    Button adminmode, quicksignin, usermode;
    EditText inputEmail, inputPass;
    ProgressBar proglog;

    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = findViewById(R.id.inputEmail);
        inputPass = findViewById(R.id.inputPass);

        btnSignup = findViewById(R.id.btnSignup);
        btnLogin  = findViewById(R.id.btnLogin);

        proglog = findViewById(R.id.proglog);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
}


    public void Register(View v) {
        Intent i = new Intent(this, Signup.class);
        startActivity(i);
    }

    public void QuickSignIn(View v) {
        Intent i = new Intent(this, Shop.class);
        startActivity(i);
    }

    public void Test(View v) {
        Intent i = new Intent(this, Admin.class);
        startActivity(i);
    }

    public void Test2(View v) {
        Intent i = new Intent(this, Cart.class);
        startActivity(i);
    }

    public void SignIn(View v){

        if(inputEmail.getText().toString().equals("") && inputPass.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Blank not allowed", Toast.LENGTH_LONG).show();
        }
        else {
            proglog.setVisibility(View.VISIBLE);
            auth.signInWithEmailAndPassword(inputEmail.getText().toString(),inputPass.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                user = auth.getCurrentUser();
                                Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
                                finish();
                                Intent i = new Intent(getApplicationContext(), WelcomeScreen.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), "Login failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }
}
