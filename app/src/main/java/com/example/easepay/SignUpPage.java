package com.example.easepay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpPage extends AppCompatActivity {

    EditText name_input, Email_input, pass_input, repass_input;
    TextView Name_SignUp , Email_SignUp , Password_SignUp , Repassword_SignUp;
    Button SignUp_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        name_input = findViewById(R.id.name_input);
        Email_input = findViewById(R.id.Email_input);
        pass_input = findViewById(R.id.pass_input);
        repass_input = findViewById(R.id.repass_input);
        Name_SignUp = findViewById(R.id.Name_Signup);
        Email_SignUp = findViewById(R.id.Email_SignUp);
        Password_SignUp = findViewById(R.id.Password_SignUp);
        Repassword_SignUp = findViewById(R.id.Repassword_SignUp);
        SignUp_Button = findViewById(R.id.SignUp_Button);

        SignUp_Button.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),LoginPage.class)));
    }
}