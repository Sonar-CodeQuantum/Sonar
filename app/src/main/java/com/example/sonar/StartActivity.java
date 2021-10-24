package com.example.sonar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    private Button goToSignUpPage;
    private Button goToLoginPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

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

    private void goToSignUpPage() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    private void goToLoginPage() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}