package com.example.agrilife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultListener;

public class MainActivity extends AppCompatActivity{
    FrameLayout container;
    BottomNavigationView bottomNavBar;
    FirebaseUser current_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = findViewById(R.id.container);

        bottomNavBar  = findViewById(R.id.bottomNav);
        bottomNavBar.setOnNavigationItemSelectedListener( navListner);
        current_user = FirebaseAuth.getInstance().getCurrentUser();
        if(current_user == null){
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
        }
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListner = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id.home:
                    selectedFragment = new HomeFragment(); break;
                case  R.id.dashboard:
                    selectedFragment = new DashBoard(); break;
                case R.id.advise:
                    selectedFragment = new AdviseFragment();break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container,
                    selectedFragment).commit();

            return true;
        }
    };


}