package com.example.sodevs;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private SignedInUser signedInUser;

    @Override
    protected void onStart() {
        super.onStart();

        Hotels.getInstance().createHotels();

        if (currentUser != null) {
            signedInUser.setEmail(currentUser.getEmail());
            Intent intent = new Intent(this, SearchHotel.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        signedInUser = SignedInUser.getInstance();
    }

    public void goToSignUpScreen(View view) {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    public void goToSignInScreen(View view) {
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    }
}
