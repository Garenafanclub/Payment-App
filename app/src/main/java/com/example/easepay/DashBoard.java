package com.example.easepay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class DashBoard extends AppCompatActivity {

    DrawerLayout drawerLayout;
    FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String user_id;

//    @SuppressLint("MissingInflatedId")
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
        Toolbar toolbar = findViewById(R.id.toolbar);
      //toolbar.setLogo(R.drawable.searchbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this , drawerLayout , toolbar,
                R.string.navigation_drawer_open , R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

       // @SuppressLint("NonConstantResourceId")
        BottomNavigationView.OnNavigationItemSelectedListener navListener =
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
                    if (savedInstanceState == null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectFragment).commit();
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                };
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

//    @SuppressLint("SuspiciousIndentation")
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
            super.onBackPressed();
    }
    public void doThis(MenuItem item){
        mAuth.signOut();
        Toast.makeText(this, "Successfully LogOut", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}