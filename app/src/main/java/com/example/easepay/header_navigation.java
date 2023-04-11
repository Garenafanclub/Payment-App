package com.example.easepay;

import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.auth.User;

import java.util.Calendar;

public class header_navigation extends AppCompatActivity {
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    String user_id;
     TextView tw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_navigation);
        Toast.makeText(this, "FUCK U", Toast.LENGTH_SHORT).show();
        tw = findViewById(R.id.fetch_l);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        tw.setText("saksham");
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