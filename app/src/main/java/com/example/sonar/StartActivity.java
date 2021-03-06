package com.example.sonar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.parse.ParseUser;

public class StartActivity extends AppCompatActivity {

    private Button goToSignUpPage;
    private Button goToLoginPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        verifyPermissions();

        // if the user is already logged in, redirect to Alert Activity
        if (ParseUser.getCurrentUser() != null) { // user is logged in
            gotoMainActivity();
        }

        goToSignUpPage = (Button) findViewById(R.id.signUpBtn);
        goToLoginPage = (Button) findViewById(R.id.loginBtn);

        goToSignUpPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignUpPage();
            }
        });

        goToLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLoginPage();
            }
        });
    }

    private void verifyPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, },
                    PackageManager.PERMISSION_GRANTED);
            return;
        }
    }

    private void goToSignUpPage() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    private void goToLoginPage() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void gotoMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}