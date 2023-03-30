package com.example.easepay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpPage extends AppCompatActivity {

    EditText name_input, Email_input, pass_input, repass_input;
    TextView Name_SignUp , Email_SignUp , Password_SignUp , Repassword_SignUp;
    Button SignUp_Button;

    private String name , email , password, repassword;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth;
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
        mAuth = FirebaseAuth.getInstance();

        SignUp_Button.setOnClickListener(v ->
        {
            name = name_input.getText().toString().trim();
            email = Email_input.getText().toString().trim();
            password = pass_input.getText().toString().trim();
            repassword = repass_input.getText().toString().trim();
            boolean a= true;
            boolean b= true;
            boolean c= true;
            boolean d= true;
            if(TextUtils.isEmpty(name))
            {
                name_input.setError("Name is Required");
                a=false;
            }
            if(TextUtils.isEmpty(email)) {
                Email_input.setError("Email is Required");
                b = false;
            }
            if(TextUtils.isEmpty(password))
            {
                pass_input.setError("password is Required");
                c=false;
            }
            if(TextUtils.isEmpty(repassword))
            {
                repass_input.setError("repassword is Required");
                d=false;
            }
            if(password.length()<=6)
            {
                pass_input.setError("password must be >= 6 characters");
                c=false;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                Email_input.setError("Please provide valid Email");
                b=false;
            }
            if(!repassword.equals(password))
            {
                repass_input.setError("Please write your same password as you provide above");
                repass_input.requestFocus();
                c=false;
            }
            if(a && b && c && d){
                // calling method to add data to Firebase Firestore.
                addDataToFirestore(name, email, password);
            }
        });
    }
    private void addDataToFirestore(String name, String email, String password) {

        // creating a collection reference
        // for our Firebase Firestore database.
        CollectionReference dbCourses = db.collection("user_login");

        // adding our data to our courses object class.
        Courses courses = new Courses(name, email, password,repassword);

        // below method is use to add data to Firebase Firestore.
        dbCourses.add(courses).addOnSuccessListener(documentReference -> {
            // after the data addition is successful
            // we are displaying a success toast message.
            Toast.makeText(SignUpPage.this, "Your account has been added to Firebase Firestore", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                Toast.makeText(SignUpPage.this, "Fail to add course \n" + e, Toast.LENGTH_SHORT).show();
                System.out.println(e);
            }
        });
    }
}