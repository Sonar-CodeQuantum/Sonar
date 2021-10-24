package com.example.sonar.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.telephony.SmsManager;

import android.util.Log;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.legacy.app.FragmentCompat;

import com.example.sonar.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.parse.ParseUser;

import java.lang.reflect.Array;

public class AlertFragment extends Fragment {

    private static final String TAG = "AlertFragment";
    private FusedLocationProviderClient fusedLocationClient;

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    private ImageButton alertbutton;
    private int alertState = 0;
    String message;
    String[] phoneNo;

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

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        // Setup any handles to view objects here
        alertbutton = view.findViewById(R.id.alertbutton);
        alertbutton.setBackgroundResource(R.drawable.alert_button);
        alertbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alertState == 0) {
                    alertbutton.setBackgroundResource(R.drawable.alert_button_red);
                    alertState = 1;
                    getMyLocation(fusedLocationClient);

                    //call method to put user's friends contact list into an Array by going through the users friends list on parse
                    getFriendsNum();
                    //another method call - pass in array and go each number sending alert messages to the friends
                    textFriends();
                } else {
                    alertbutton.setBackgroundResource(R.drawable.alert_button);
                    alertState = 0;

                }

            }
        });
    }

    public void getMyLocation(FusedLocationProviderClient fusedLocationClient) {
        // Get last known recent location using new Google Play Services SDK (v11+)
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, },
                    PackageManager.PERMISSION_GRANTED);
            return;
        }
        this.fusedLocationClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // GPS location can be null if GPS is switched off
                        if (location != null) {
                            Log.i(TAG, "retrieved location: " + location.getLatitude() + "," + location.getLongitude());
                            Toast.makeText(getContext(), "Location retrieved. Sending alert!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("MapDemoActivity", "Error trying to get last GPS location");
                        e.printStackTrace();
                    }
                });
    }

    private void textFriends(/*pass array*/) {
        //phoneNo =;
        message = "You friend has set off an alert!";

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.SEND_SMS)) {
            } else {
                requestPermissions(new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }

    private void getFriendsNum() {


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();

                    //for loop around phone number array here!!
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);

                    Toast.makeText(getActivity(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }
}