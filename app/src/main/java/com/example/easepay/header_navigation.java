package com.example.easepay;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class header_navigation extends AppCompatActivity {
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    String user_id;
     TextView tw;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_navigation);
        Toast.makeText(this, "FUCK U", Toast.LENGTH_SHORT).show();
        tw = findViewById(R.id.fetch_l);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct != null)
        {
            String PersonName = acct.getDisplayName();
            tw.setText(PersonName);
        }

//        FirebaseUser user = mAuth.getCurrentUser();
//        if(user == null)
//        {
//            startActivity(new Intent(getApplicationContext(),DashBoard.class));
//        }
//        db.collection("user_login").document(user_id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                String name  = documentSnapshot.getString("fName").toString();
//                   fname = name.split("\\s");
//                   act_name = fname[0];
//                   tw.setText(act_name);
//            }
//        });
    }

}