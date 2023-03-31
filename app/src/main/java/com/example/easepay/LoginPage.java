package com.example.easepay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginPage extends AppCompatActivity {

    TextView Email_Login , Password_Login , ForgetPass_Login;
    ProgressBar bar_login ;
    EditText EmailName_Login , PasswordName_Login;
    Button Login_Button;
    private String email , password;
    FirebaseAuth mAuth;

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
        bar_login = findViewById(R.id.bar_login);
        mAuth = FirebaseAuth.getInstance();

        FirebaseFirestore db = FirebaseFirestore.getInstance();


        Login_Button.setOnClickListener(v ->
        {
            loginUser();
        });
    }
    private void loginUser()
    {
        email = EmailName_Login.getText().toString().trim();
        password = PasswordName_Login.getText().toString().trim();
       boolean b = true;
       boolean c = true;
        if(TextUtils.isEmpty(email)) {
            EmailName_Login.setError("Email is Required");
            EmailName_Login.requestFocus();
            b = false;
        }
        else if(TextUtils.isEmpty(password))
        {
            PasswordName_Login.setError("password is Required");
            PasswordName_Login.requestFocus();
            c=false;
        }
       else if(password.length()<=6)
        {
            PasswordName_Login.setError("password must be >= 6 characters");
            c=false;
        }
       else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            EmailName_Login.setError("Please provide valid Email");
            b=false;
        }
        else if(b && c) {
           mAuth.signInWithEmailAndPassword(email ,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful())
                   {
                       Toast.makeText(getApplicationContext(),"User login is successfully",Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(getApplicationContext(),DashBoard.class));
                       bar_login.getProgress();
                       overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                   }
                   else{
                       Toast.makeText(getApplicationContext(),"Login Error: "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                   }
               }
           });
        }
    }
}