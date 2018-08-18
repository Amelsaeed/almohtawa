package com.example.ahmedmagdy.almohtawa;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    private EditText editTextEmail, editTextPassowrd;
    private Button buttonSignin;
    private TextView textViewSignUp;

    private FirebaseAuth mAuth;

    String TAG = "TECSTORE";
/**
    EditText emailEt, passwordEt;
    Button loginBtn;
    TextView signUpTextview;
**/
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.edit_username);
        editTextPassowrd = findViewById(R.id.edit_password);
        buttonSignin = findViewById(R.id.button_signin);
        textViewSignUp = findViewById(R.id.textview_signup);
        /**
      if(mAuth.getInstance()!= null){

           startActivity(new Intent(getApplicationContext(),Main2Activity.class));
        finish();
       }
         **/
/**
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }**/

        buttonSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextEmail.length() > 0 && editTextPassowrd.length() > 0) {
                    if (editTextEmail.length() >= 8 && editTextPassowrd.length() >= 8) {
                       // String greetings = "Welcome back " + editTextEmail.getText().toString().trim() + "!";
                        signInUser(editTextEmail.getText().toString(), editTextPassowrd.getText().toString());
                      //  Toast.makeText(login.this, greetings, Toast.LENGTH_LONG).show();
                    } else {
                        if (editTextEmail.length() < 8) {
                            Toast.makeText(login.this, "Your username is too short!", Toast.LENGTH_LONG).show();
                        }

                        if (editTextPassowrd.length() < 8) {
                            Toast.makeText(login.this, "Your password is too short!", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(login.this, "Please enter both your username & password!", Toast.LENGTH_LONG).show();
                }
            }
        });
        initAuthStateListener();


        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open SignUp Activity
                startActivity(new Intent(login.this, signup.class));
            }
        });

    }
    private void initAuthStateListener() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }


    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void signInUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "SIGNIN SUCCESS");
                    Toast.makeText(login.this, "SIGNIN SUCCESS", Toast.LENGTH_SHORT).show();
                    Intent iii= new Intent(login.this, Main2Activity.class);
                    startActivity(iii);
                } else {
                    Log.e(TAG, "SIGNIN ERROR");
                    Toast.makeText(login.this, "SIGNIN ERROR", Toast.LENGTH_SHORT).show();

                }
            }

        });
    }




}
