package com.example.easepay;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button  button_loginb , button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_loginb = findViewById(R.id.button_loginb);
        button = findViewById(R.id.button);

        button_loginb.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),LoginPage.class)));
        button.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),SignUpPage.class)));
    }
}