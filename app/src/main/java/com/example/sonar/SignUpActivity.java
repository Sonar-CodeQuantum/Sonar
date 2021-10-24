package com.example.sonar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class SignUpActivity extends AppCompatActivity {

    public static final String TAG = "SignUpActivity";
    private EditText etNumber;
    private EditText etName;
    private EditText etPassword;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // get view items
        etNumber = findViewById(R.id.etNumber);
        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Signing up as a new user with the provided fields
                String number = etNumber.getText().toString();
                String username = etName.getText().toString();
                String password = etPassword.getText().toString();

                //Create the ParseUser
                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setPassword(password);
                user.put("mobile", etNumber);

                //Invoke signUpBackground
                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            //sign up successful
                            gotoMainActivity();
                        } else {
                            //Sign up was not successful .. look at the ParseException
                            //to figure out what went wrong
                            Toast.makeText(SignUpActivity.this, "Sign up not successful", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "Parse Exception: " + e);

                        }
                    }
                });

            }

        });

    }

    private void gotoMainActivity() {
        Toast.makeText(SignUpActivity.this, "Account Created!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
