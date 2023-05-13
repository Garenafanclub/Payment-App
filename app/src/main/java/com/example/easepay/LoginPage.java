package com.example.easepay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginPage extends AppCompatActivity {

    TextView Email_Login , Password_Login , ForgetPass_Login;
    ProgressDialog progressDialog;
    EditText EmailName_Login , PasswordName_Login;
    Button Login_Button;
    Button Google_button , Facebook_button;
    private String email , password;
    FirebaseAuth mAuth;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

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
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        Google_button = findViewById(R.id.Google_Button);
        Facebook_button = findViewById(R.id.Facebook_Button);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this,gso);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Facebook_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PhoneAuthLogin.class));
            }
        });

        String titleId = "Processing...";
        progressDialog.setTitle(titleId);
        progressDialog.setMessage("Please wait a moment");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);

        Google_button.setOnClickListener(v -> signIn());

        Login_Button.setOnClickListener(v -> loginUser());
    }
    public void onStart()
    {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!= null)
        {
            startActivity(new Intent(getApplicationContext(),DashBoard.class));
        }
    }
    private void signIn()
    {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1002);
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode , Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 1002)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Toast.makeText(this, "Error in getting Google's Info", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void firebaseAuthWithGoogle(String idToken)
    {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken,null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(getApplicationContext(),DashBoard.class));
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Problem found in Firebase Login",Toast.LENGTH_SHORT).show();
                        }
                    }
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
            progressDialog.show();
           mAuth.signInWithEmailAndPassword(email ,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful())
                   {
                       progressDialog.dismiss();
                       Toast.makeText(getApplicationContext(),"Successfully Logged in",Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(getApplicationContext(),DashBoard.class));
                       overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                   }
                   else{
                       progressDialog.dismiss();
                       Toast.makeText(getApplicationContext(),"Login Error: "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                   }
               }
           });
        }
    }
}