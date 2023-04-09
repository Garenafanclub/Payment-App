package com.example.easepay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.jetbrains.annotations.NonNls;

public class DashBoard extends AppCompatActivity {

    DrawerLayout drawerLayout;
    TextView textView;
    public static String act_name;
    public static String fname[];
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth;
    String user_id;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
//        NavigationView navigationView = (NavigationView) findViewById(R.id.navView);
//        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
//        View header= navigationView.getHeaderView(0);


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        textView = findViewById(R.id.fetch_name);
        mAuth = FirebaseAuth.getInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();

        Toolbar toolbar = findViewById(R.id.toolbar);
//        toolbar.setLogo(R.drawable.searchbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this , drawerLayout , toolbar,
                R.string.navigation_drawer_open , R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

         @SuppressLint("NonConstantResourceId") BottomNavigationView.OnNavigationItemSelectedListener navListener =
                item -> {
                    Fragment selectFragment = null;
                    switch (item.getItemId())
                    {
                        case R.id.nav_home:
                            selectFragment = new HomeFragment();
                            break;
                        case R.id.nav_mall:
                            selectFragment = new MallFragment();
                            break;
                        case R.id.nav_bank:
                            selectFragment = new BankFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectFragment).commit();
                    return true;
                };
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    @SuppressLint("SuspiciousIndentation")
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
            super.onBackPressed();
    }
//    public void onStart() {
//
//        super.onStart();
//        FirebaseUser user = mAuth.getCurrentUser();
//        if(user == null)
//        {
//            startActivity(new Intent(getApplicationContext(),MainActivity.class));
//        }
//        user_id = mAuth.getCurrentUser().getUid();
//        DocumentReference documentReference = db.collection("user_login").document(user_id);
//        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                   String name = value.getString("fName").toString();
//                   fname = name.split("\\s");
//                   act_name = fname[0];
//                   textView.setText(fname[0]);
//            }
//        });
//    }

}