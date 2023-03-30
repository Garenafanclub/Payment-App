package com.example.easepay;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button  button_loginb , button;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_loginb = findViewById(R.id.button_loginb);
        button = findViewById(R.id.button);
        mAuth = FirebaseAuth.getInstance();
    }
       @Override
       protected void onStart() {

           super.onStart();
           FirebaseUser user = mAuth.getCurrentUser();
           if(user == null)
           {
               startActivity(new Intent(getApplicationContext(),LoginPage.class));
           }
       }
}