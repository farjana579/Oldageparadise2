package com.example.oldageparadise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity {

    //variable
    public EditText signInEmailEditText, signInPasswordEditext;
    public Button signInButton, SignUpButton;
    public ProgressBar signInprogress;
    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mAuth = FirebaseAuth.getInstance();
        //finding by id
        signInEmailEditText = (EditText) findViewById(R.id.useremailsignIn);
        signInPasswordEditext = (EditText) findViewById(R.id.passwordsignIn);
        signInButton = (Button) findViewById(R.id.login);
        SignUpButton = (Button) findViewById(R.id.SignUpLogIn);
        signInprogress = (ProgressBar)findViewById(R.id.loading);
        //for a new account
        findViewById(R.id.SignUpLogIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(ii);
            }
        });

        //for user login
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.login:
                        userlogin();
                        break;
                }
            }
        });
    }

    private void userlogin() {
        String email = signInEmailEditText.getText().toString().trim();
        String password = signInPasswordEditext.getText().toString().trim();

        //checking email is empty or not
        if (email.isEmpty()) {
            signInEmailEditText.setError("Enter an email address");
            signInEmailEditText.requestFocus();
            return;
        }

        // check email is valid or not>
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signInEmailEditText.setError("Enter a valid email address");
            signInEmailEditText.requestFocus();
            return;
        }
        //check password is empty or not?
        if (password.isEmpty()) {
            signInPasswordEditext.setError("Enter a password");
            signInPasswordEditext.requestFocus();
            return;
        }
        if (password.length() < 6) {
            signInPasswordEditext.setError("Minimum password length should be 6");
            signInPasswordEditext.requestFocus();
            return;
        }
        signInprogress.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                signInprogress.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    //no need to come this activity again
                   finish();
                    Intent intent = new Intent(getApplicationContext(),MentalHealthCare.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Login succesful", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Login unsuccesful", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
