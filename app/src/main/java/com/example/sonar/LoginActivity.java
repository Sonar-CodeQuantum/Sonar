package com.example.sonar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseUser;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";

    private EditText etNumber;
    private EditText etName;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etNumber = findViewById(R.id.etNumber);
        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnSignUp);

        etNumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etName.getText().toString();
                String password = etPassword.getText().toString();
                String number = etNumber.getText().toString();
                Log.i(TAG, "logging in user as " + username);
                loginUser(username, password, number);
            }
        });
    }

    private void loginUser(String username, String password, String number) {
        Log.i(TAG, "Attempting to login user: " + username);

        // verify user and navigate to login on success
        if (PhoneNumberUtils.isGlobalPhoneNumber(number)) {
            Toast.makeText(LoginActivity.this,
                    "Invalid phone number.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        ParseUser.logInInBackground(username, password, (user, e) -> {
            if (user == null || e != null) { // login failure
                Log.e(TAG, "Login unsuccessful: " + e);
                Toast.makeText(LoginActivity.this,
                        "Incorrect username and/or password",
                        Toast.LENGTH_SHORT).show();
            } else { //success
                gotoMainActivity();
            }
        });
    }

    private void gotoMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}