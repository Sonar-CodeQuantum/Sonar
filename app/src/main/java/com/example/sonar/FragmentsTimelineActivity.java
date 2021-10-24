package com.example.sonar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FragmentsTimelineActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_button);

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_friends_list:
                        Toast.makeText(FragmentsTimelineActivity.this, "Friends List", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_alert:
                        Toast.makeText(FragmentsTimelineActivity.this, "Alert", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_profile:
                        Toast.makeText(FragmentsTimelineActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                    default:
                        break;
                }
                return true;
            }
        });
    }
}
