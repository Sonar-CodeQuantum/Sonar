package com.example.sonar.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sonar.MainActivity;
import com.example.sonar.R;
import com.example.sonar.adapters.FriendsAdapter;
import com.example.sonar.models.Friend;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class FriendsListFragment extends Fragment {

    private static final String TAG = "FriendsListFragment";
    LinearLayout addFriendContainer;
    TextView tvAddFriend;
    ImageView ivAddFriend;
    TextView tvEmptyMsg;

    RecyclerView rvFriends;
    FriendsAdapter adapter;

    private List<Friend> friendsList;

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
        tvEmptyMsg = view.findViewById(R.id.tvEmptyMsg);
        rvFriends = view.findViewById(R.id.rvFriends);

        // populate rv
        friendsList = new ArrayList<>();
        adapter = new FriendsAdapter(getContext(), friendsList);
        rvFriends.setAdapter(adapter);
        rvFriends.setLayoutManager(new LinearLayoutManager(getContext()));
        queryFriends();

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

    @SuppressLint("NotifyDataSetChanged")
    private void queryFriends() {
        ParseQuery<Friend> query = ParseQuery.getQuery(Friend.class);
        query.include(Friend.KEY_USER); // include ref to user key
        query.whereEqualTo(Friend.KEY_USER, ParseUser.getCurrentUser()); // get friends of this user
        query.addDescendingOrder("name");

        // start async query for posts
        query.findInBackground((friends, e) -> {
            if (e != null) { // error
                Log.e(TAG, "Parse Exception while retrieving friends: " + e);
                return;
            } else { // on success
                if (friends.isEmpty()) {
                    tvEmptyMsg.setText("You have yet to add friends to your list!" +
                            System.getProperty("line.separator") +
                            "Add friends to notify them when you have an alert!");
                } else {
                    // list friends
                    for (Friend friend : friends) {
                        Log.i(TAG, "Reading friend: " + friend.getName()
                                + " @" + friend.getNumber());
                    }
                    friendsList.addAll(friends);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}