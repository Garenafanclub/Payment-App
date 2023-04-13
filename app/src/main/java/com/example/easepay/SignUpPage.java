package com.example.easepay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpPage extends AppCompatActivity {

    EditText name_input, Email_input, pass_input, repass_input;
    ProgressDialog pd;
    TextView Name_SignUp , Email_SignUp , Password_SignUp , Repassword_SignUp;
    Button SignUp_Button;
    String user_id;

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
        pd = new ProgressDialog(this);
        String titleId = "Signing in...";
        pd.setTitle(titleId);
        pd.setMessage("Please Wait...");
        pd.setCancelable(false);
        pd.setIndeterminate(true);

        SignUp_Button.setOnClickListener(v ->
        {
            createUser();
        });
    }
    public void createUser()
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
            name_input.requestFocus();
            a=false;
        }
        if(TextUtils.isEmpty(email)) {
            Email_input.setError("Email is Required");
            Email_input.requestFocus();
            b = false;
        }
        if(TextUtils.isEmpty(password))
        {
            pass_input.setError("password is Required");
            pass_input.requestFocus();
            c=false;
        }
        if(TextUtils.isEmpty(repassword))
        {
            repass_input.setError("repassword is Required");
            repass_input.requestFocus();
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
            pd.show();
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful())
                   {
                       pd.dismiss();
                       Toast.makeText(getApplicationContext(),"User registered successfully",Toast.LENGTH_SHORT).show();
                       user_id = mAuth.getCurrentUser().getUid();

                       DocumentReference documentReference = db.collection("user_login").document(user_id);
                       Map<String,Object> user = new HashMap<>();
                       user.put("fName",name_input.getText().toString());
                       user.put("Email",Email_input.getText().toString());

                       documentReference.set(user).addOnSuccessListener(unused -> { });
                       startActivity(new Intent(getApplicationContext(),LoginPage.class));

                       overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                   }
                   else{
                       pd.dismiss();
                       Toast.makeText(getApplicationContext(),"Registration Error: "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                   }
                }
            });
        }
    }
}