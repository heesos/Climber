package com.diploma.climber.cards;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.diploma.climber.R;
import com.diploma.climber.domain.User;

import java.util.List;

public class CardStackAdapter extends RecyclerView.Adapter<CardStackAdapter.ViewHolder> {

    private List<User> users;

    public CardStackAdapter(List<User> addList) {
        this.users = addList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.user_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setItems(List<User> items) {
        this.users = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profilePicture;
        TextView name, description, styles;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profilePicture = itemView.findViewById(R.id.profilePhoto);
            name = itemView.findViewById(R.id.nameView);
            description = itemView.findViewById(R.id.descriptionView);
            styles = itemView.findViewById(R.id.stylesView);
        }

        public void setData(User user) {
            profilePicture.setImageBitmap(user.getProfilePicture());
            name.setText(user.getName());
            description.setText(user.getDescription());
            styles.setText(user.getClimbingTypes().toString());
        }
    }
}
