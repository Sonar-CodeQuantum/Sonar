package com.example.sonar.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.sonar.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlertFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlertFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ImageButton alertbutton;
    private int alertState = 0;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AlertFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AlertFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AlertFragment newInstance(String param1, String param2) {
        AlertFragment fragment = new AlertFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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