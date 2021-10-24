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

import com.example.sonar.R;
import com.example.sonar.models.Friend;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class AlertFragment extends Fragment {

    private static final String TAG = "AlertFragment";
    private FusedLocationProviderClient fusedLocationClient;

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    private ImageButton alertbutton;
    private int alertState = 0;
    String message;

    ArrayList<String> friendNums;

    public class Coordinates {
        private double latitude;
        private double longitude;
        public Coordinates(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }
        public double getLatitude() {  return latitude; }
        public double getLongitude() {  return longitude; }
    }

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
                    alert();
                } else {
                    alertbutton.setBackgroundResource(R.drawable.alert_button);
                    alertState = 0;
                }
            }
        });
    }

    private void alert() {
        Coordinates coords = getLocationCoords(fusedLocationClient);
        friendNums = getFriendsNum();
        textFriends(coords);
    }

    public Coordinates getLocationCoords(FusedLocationProviderClient fusedLocationClient) {
        // Get last known recent location using new Google Play Services SDK (v11+)
        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, },
                    PackageManager.PERMISSION_GRANTED);
        }
        Coordinates coords = null;
        this.fusedLocationClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // GPS location can be null if GPS is switched off
                        if (location != null) {
                            // LOCATION ACQUIRED
                            Coordinates coords = new Coordinates(location.getLatitude(), location.getLongitude());
                            Log.i(TAG, "retrieved location: " +
                                    coords.getLatitude() + "," + coords.getLongitude());
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
        return coords;
    }

    private ArrayList<String> getFriendsNum() {
        ArrayList<String> numbers = new ArrayList<>();
        ParseQuery<Friend> query = ParseQuery.getQuery(Friend.class);
        query.include(Friend.KEY_USER); // include ref to user key
        query.whereEqualTo(Friend.KEY_USER, ParseUser.getCurrentUser()); // get friends of this user

        // start async query for friends
        query.findInBackground(new FindCallback<Friend>() {
            @Override
            public void done(List<Friend> friends, ParseException e) {
                if (e != null) { // error
                    Log.e(TAG, "Parse Exception while retrieving friends: " + e);
                } else { // on success
                    for (Friend friend : friends) {
                        numbers.add(friend.getNumber());
                    }
                }
            }
        });
        return numbers;
    }

    private void textFriends(Coordinates coords) {
        ParseUser user = ParseUser.getCurrentUser();

        String baseUrl = "https://maps.google.com/maps?q=%f,%f";
        String url = String.format(baseUrl, coords.getLatitude(), coords.getLongitude());

        message = "You friend has set off an emergency alert! Please take diligent action!\n" +
                "Name: " + user.getUsername() + "\nLocation: " + url +
                "\n\n(Sent from the Sonar App)";

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

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();

                    for (String phoneNo : friendNums) {
                        smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    }
                    Toast.makeText(getActivity(), "Alerts sent.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(),
                            "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
    }
}