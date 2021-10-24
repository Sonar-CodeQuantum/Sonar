package com.example.sonar.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sonar.MainActivity;
import com.example.sonar.R;
import com.example.sonar.SignUpActivity;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class AddFriendFragment extends Fragment {

    private static final String TAG = "AddFriendFragment";

    Button btnConfirm;
    ImageView ivPhoto;
    EditText etName;
    EditText etNumber;

    public AddFriendFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_friend, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnConfirm = view.findViewById(R.id.btnConfirm);
        ivPhoto = view.findViewById(R.id.ivPhoto);
        etName = view.findViewById(R.id.etName);
        etNumber = view.findViewById(R.id.etNumber);

        etNumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String number = etNumber.getText().toString();

                //validate number
                if (PhoneNumberUtils.isGlobalPhoneNumber(number)) {
                    Log.e(TAG, "Invalid phone number: " + number);
                    Toast.makeText(getActivity(),
                            "Invalid phone number.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                String normNumber = PhoneNumberUtils.normalizeNumber(number);

                if (name.isEmpty() || normNumber.isEmpty()) {
                    Toast.makeText(getActivity(),
                            "Please provide a name and number.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                addFriend(name, normNumber);
            }

            private void addFriend(String name, String number) {
                // create new friend object
                ParseObject newFriend = new ParseObject("Friends");
                newFriend.put("user", ParseUser.getCurrentUser());
                newFriend.put("name", name);
                newFriend.put("mobile", number);
                // newFriend.put("photo", new ParseFile("resume.txt", "My string content".getBytes()));

                // Saves new object.
                newFriend.saveInBackground(e -> {
                    if (e == null) { // on success
                        Toast.makeText(getActivity(),
                                "Added " + newFriend.getString("name"),
                                Toast.LENGTH_SHORT).show();
                    } else { // on failure
                        Log.e(TAG, "Error occured while adding friend" +
                                "\nError: " + e);
                        Toast.makeText(getActivity(),
                                "Could not add friend",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}