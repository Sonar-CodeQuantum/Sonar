package com.example.sonar.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sonar.MainActivity;
import com.example.sonar.R;
import com.parse.ParseUser;

public class FriendsListFragment extends Fragment {

    LinearLayout addFriendContainer;
    TextView tvAddFriend;
    ImageView ivAddFriend;

    public FriendsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friends_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addFriendContainer = view.findViewById(R.id.addFriendContainer);
        tvAddFriend = view.findViewById(R.id.tvAddFriend);
        ivAddFriend = view.findViewById(R.id.ivAddFriend);

        View.OnClickListener addFriendOnClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().
                        replace(R.id.flContainer, new AddFriendFragment()).
                        addToBackStack(null).
                        commit();
            }
        };

        addFriendContainer.setOnClickListener(addFriendOnClick);
        tvAddFriend.setOnClickListener(addFriendOnClick);
        ivAddFriend.setOnClickListener(addFriendOnClick);
    }
}