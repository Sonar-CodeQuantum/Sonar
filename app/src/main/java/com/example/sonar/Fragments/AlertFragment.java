package com.example.sonar.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.example.sonar.R;

public class AlertFragment extends Fragment {

    private ImageButton alertbutton;
    private int alertState = 0;

    public AlertFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alert, container, false);


    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        alertbutton = view.findViewById(R.id.alertbutton);
        alertbutton.setBackgroundResource(R.drawable.alert_button);
        alertbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alertState == 0)
                {
                    alertbutton.setBackgroundResource(R.drawable.alert_button_red);
                    alertState = 1;
                }
                else{
                    alertbutton.setBackgroundResource(R.drawable.alert_button);
                    alertState = 0;
                }
            }
        });
    }
}