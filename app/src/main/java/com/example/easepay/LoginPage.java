package com.example.easepay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {

    TextView Email_Login , Password_Login , ForgetPass_Login;
    EditText EmailName_Login , PasswordName_Login;
    Button Login_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        Email_Login = findViewById(R.id.Email_Login);
        Password_Login = findViewById(R.id.Password_Login);
        ForgetPass_Login = findViewById(R.id.ForgetPass_Login);
        EmailName_Login = findViewById(R.id.EmailName_Login);
        PasswordName_Login = findViewById(R.id.PasswordName_Login);
        Login_Button = findViewById(R.id.Login_Button);


        Login_Button.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),MainActivity.class)));
    }
}