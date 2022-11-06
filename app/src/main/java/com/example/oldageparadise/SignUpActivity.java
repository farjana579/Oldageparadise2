
package com.example.oldageparadise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class  SignUpActivity extends AppCompatActivity {

    //variable
    public EditText signUpUserNameEditText ,signUpEmailEditText, signUpPasswordEditext,
            signUpConfirmPasswordEditext;
    public Button signupButton;
    public ProgressBar signUpprogress;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        //finding by id
        signUpEmailEditText = (EditText) findViewById(R.id.signUpuseremail);
        signUpprogress = (ProgressBar) findViewById(R.id.signUploading);
        signUpConfirmPasswordEditext = (EditText) findViewById(R.id.Signupconfirm_password);
        signupButton = (Button)findViewById(R.id.SignUpButton);



        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                 //for a new register
                    case R.id.SignUpButton:
                           newusersignUp();
                   break;
                }
            }
        });


    }

    private void newusersignUp() {

        String email = signUpEmailEditText.getText().toString().trim();
        String password = signUpConfirmPasswordEditext.getText().toString().trim();

        //checking email is empty or not
        if(email.isEmpty()){
            signUpEmailEditText.setError("Enter an email address");
            signUpEmailEditText.requestFocus();
            return;
        }

        // check email is valid or not>
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            signUpEmailEditText.setError("Enter a valid email address");
            signUpEmailEditText.requestFocus();
            return;
        }
        //check password is empty or not?
        if(password.isEmpty()){
            signUpConfirmPasswordEditext.setError("Enter a password");
            signUpConfirmPasswordEditext.requestFocus();
            return;
        }
        if(password.length()<6){
            signUpConfirmPasswordEditext.setError("Minimum password length should be 6");
            signUpConfirmPasswordEditext.requestFocus();
            return;
        }
        signUpprogress.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                signUpprogress.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    //no need to come this activity again
                    finish();
                    Intent intent = new Intent(getApplicationContext(),SplashScreen.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Register is Succesful",Toast.LENGTH_LONG).show();
                }
                else {
                    //user is already sign up
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), " User is already Registered", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Error: "+task.getException().getMessage(), Toast.LENGTH_LONG).show();

                    }
                    }
                }

        });

    }
}