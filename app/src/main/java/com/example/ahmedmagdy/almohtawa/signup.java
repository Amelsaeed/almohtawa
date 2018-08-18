package com.example.ahmedmagdy.almohtawa;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class signup extends AppCompatActivity {
    private EditText editTextName,  editTextPassowrd, editTextEmail;
    private Spinner spinnergrade;
    private Button buttonSignup;
    private TextView textViewSignin;
    /**EditText emailEt, passwordEt;
    Button signupBtn;
    TextView signUpTextview;**/
    String TAG = "TECSTORE";
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        editTextName = findViewById(R.id.edit_name);
        editTextPassowrd = findViewById(R.id.edit_password);
        editTextEmail = findViewById(R.id.edit_email);
        spinnergrade = findViewById(R.id.spinner_country);
        buttonSignup = findViewById(R.id.button_signup);
        textViewSignin = findViewById(R.id.textview_signin);



        editTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!validateEmail(editTextEmail.getText().toString())) {
//                    Toast.makeText(SignUpActivity.this, "Invalid email address!", Toast.LENGTH_SHORT).show();
                    editTextEmail.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                    editTextEmail.requestFocus();
                } else {
                    editTextEmail.setTextColor(getResources().getColor(R.color.colorText));
                }
            }
        });

        ArrayAdapter<CharSequence> adapterElectricity = ArrayAdapter.createFromResource(
                signup.this, R.array.grade_array, android.R.layout.simple_spinner_item);
        adapterElectricity.setDropDownViewResource(R.layout.list_item);
        spinnergrade.setAdapter(adapterElectricity);

        spinnergrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.colorText));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextName.getText().length() > 0 && validateEmail(editTextEmail.getText().toString().trim()) && editTextPassowrd.getText().length() >= 8) {
                    String accountDetails = "Your account is created successfully\n";
                    accountDetails += "Full name: " + editTextName.getText().toString().trim() + "\n";

                    accountDetails += "Email: " + editTextEmail.getText().toString().trim() + "\n";
                    accountDetails += "Country: " + spinnergrade.getSelectedItem().toString().trim() + "\n";
                    Toast.makeText(signup.this, accountDetails, Toast.LENGTH_LONG).show();
                    signupUser(editTextEmail.getText().toString(), editTextPassowrd.getText().toString());
                } else {
                    Toast.makeText(signup.this, "Please enter your account details!", Toast.LENGTH_LONG).show();
                }
            }
        });

        textViewSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signup.this, login.class));
            }
        });
    }

    /**
     * Check if the email address that user entered is valid or not
     *
     * @param email the email address that user enterd in signup activity
     */
    private static boolean validateEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern) & email.length() > 0;
    }

    private void signupUser(final String email, final String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    userprofile();
                    Toast.makeText(signup.this, "USER CREATED", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e(TAG, task.getException().getMessage());
                    Toast.makeText(signup.this, "an error occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void userprofile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(editTextName.getText().toString().trim() )
                //.setDisplayName(spinnergrade.getSelectedItem().toString().trim() )

               // .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                        }
                    }
                });
    }
}
