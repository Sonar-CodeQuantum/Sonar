package com.example.sonar.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sonar.R;
import com.example.sonar.models.Friend;
import com.parse.ParseFile;

import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {

    private Context context;
    private List<Friend> friends;

    public FriendsAdapter(Context context, List<Friend> friends) {
        this.context = context;
        this.friends = friends;
    }

    @NonNull
    @Override
    public FriendsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_friend, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsAdapter.ViewHolder holder, int position) {
        Friend friend = friends.get(position);
        holder.bind(friend);
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // view elements from item_friend layout
        private ImageView ivProfilePicture;
        private TextView tvName;
        private TextView tvNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivProfilePicture = itemView.findViewById(R.id.ivProfilePicture);
            tvName = itemView.findViewById(R.id.tvName);
            tvNumber = itemView.findViewById(R.id.tvNumber);
        }

        public void bind(Friend friend) {
            // bind data to view elements
            tvName.setText(friend.getName());
            tvNumber.setText(friend.getNumber());

            ParseFile image = friend.getPhoto();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivProfilePicture);
            }
        }
    }

    // Clean all elements of the recycler
    @SuppressLint("NotifyDataSetChanged")
    public void clear() {
        friends.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    @SuppressLint("NotifyDataSetChanged")
    public void addAll(List<Friend> list) {
        friends.addAll(list);
        notifyDataSetChanged();
    }
}
