package com.example.sodevs;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class SignIn extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            signInSuccessful(currentUser);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();

        final EditText emailInput = findViewById(R.id.email_input);
        final EditText passwordInput = findViewById(R.id.password_input);
        final Button submit = findViewById(R.id.sign_in_btn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emailAddress = emailInput.getText().toString();
                String password = passwordInput.getText().toString();
                boolean isValid = true;

                if (emailAddress.equals("")) {
                    emailInput.setError("Email is required");
                    isValid = false;
                }

                if (!verifyEmailAddress(emailAddress)) {
                    Toast.makeText(
                            SignIn.this,
                            "Email address is incorrect",
                            Toast.LENGTH_LONG).show();
                    isValid = false;
                }

                if (password.equals("")) {
                    passwordInput.setError("Password is required");
                    isValid = false;
                }

                if (isValid) {
                    mAuth.signInWithEmailAndPassword(emailAddress, password)
                        .addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignIn.this,
                                            "Sign in successful",
                                            Toast.LENGTH_LONG).show();
                                    signInSuccessful(mAuth.getCurrentUser());
                                } else {
                                    Toast.makeText(SignIn.this,
                                            "Sign in failed",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                }
            }
        });
    }

    private boolean verifyEmailAddress(String emailAddress) {
        Pattern ptr = Pattern.compile("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}");
        return ptr.matcher(emailAddress).matches();
    }

    private void signInSuccessful(FirebaseUser user) {
        if (user != null) {
            SignedInUser.getInstance().setEmail(user.getEmail());
            Intent intent = new Intent(this, SearchHotel.class);
            startActivity(intent);
        }
    }
}
