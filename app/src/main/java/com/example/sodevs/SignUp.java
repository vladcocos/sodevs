package com.example.sodevs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            signUpSuccessful(currentUser);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText firstNameInput = findViewById(R.id.first_name);
        final EditText lastNameInput = findViewById(R.id.last_name);
        final EditText emailInput = findViewById(R.id.email_input);
        final EditText passwordInput = findViewById(R.id.password_input);
        final EditText confirmPasswordInput = findViewById(R.id.confirm_password_input);
        final CheckBox tc = findViewById(R.id.tc_box);
        final Button submit = findViewById(R.id.sign_up_btn);

        mAuth = FirebaseAuth.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameInput.getText().toString();
                String lastName = lastNameInput.getText().toString();
                String emailAddress = emailInput.getText().toString();
                String password = passwordInput.getText().toString();
                String confirmPassword = confirmPasswordInput.getText().toString();
                boolean isAccepted = tc.isChecked();
                boolean isValid = true;

                if (firstName.equals("")) {
                    firstNameInput.setError("First name is required");
                    isValid = false;
                }

                if (lastName.equals("")) {
                    lastNameInput.setError("Last name is required");
                    isValid = false;
                }

                if (emailAddress.equals("")) {
                    emailInput.setError("Email is required");
                    isValid = false;
                }

                if (!verifyEmailAddress(emailAddress)) {
                    Toast.makeText(
                            SignUp.this,
                            "Email address is incorrect",
                            Toast.LENGTH_LONG).show();
                    isValid = false;
                }

                if (password.equals("")) {
                    passwordInput.setError("Password is required");
                    isValid = false;
                }

                if (confirmPassword.equals("")) {
                    confirmPasswordInput.setError("Password is required");
                    isValid = false;
                }

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(
                            SignUp.this,
                            "Passwords do not match",
                            Toast.LENGTH_LONG).show();
                    isValid = false;
                }

                if (!isAccepted) {
                    Toast.makeText(
                            SignUp.this,
                            "You must accept your T&C",
                            Toast.LENGTH_LONG).show();
                    isValid = false;
                }

                if (isValid) {
                    final UserProfileChangeRequest setName = new UserProfileChangeRequest.Builder()
                        .setDisplayName(firstName + " " + lastName)
                        .build();

                    mAuth.createUserWithEmailAndPassword(emailAddress, password)
                        .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignUp.this,
                                            "Sign up successful",
                                            Toast.LENGTH_LONG).show();

                                    mAuth.getCurrentUser().updateProfile(setName);

                                    signUpSuccessful(mAuth.getCurrentUser());
                                } else {
                                    Toast.makeText(SignUp.this,
                                            "Sign up failed",
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

    private void signUpSuccessful(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(this, SearchHotel.class);
            SignedInUser.getInstance().setEmail(user.getEmail());
            //intent.putExtra("firstname", user.getDisplayName());
            //intent.putExtra("email", user.getEmail());
            startActivity(intent);
        }
    }
}
