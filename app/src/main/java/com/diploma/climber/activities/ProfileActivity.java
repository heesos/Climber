package com.diploma.climber.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.diploma.climber.R;
import com.diploma.climber.domain.IdHolder;
import com.diploma.climber.domain.User;
import com.diploma.climber.viewModel.UserViewModel;


public class ProfileActivity extends AppCompatActivity {
    private TextView name;
    private TextView description;
    private TextView styles;
    private ImageView image;
    private UserViewModel userViewModel;
    private Button find, contacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initializeVariables();
        setUser();
        setListeners();
    }



    private void initializeVariables() {
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        styles = findViewById(R.id.styles);
        image = findViewById(R.id.imageView2);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        find = findViewById(R.id.button3);
        contacts = findViewById(R.id.button4);
    }

    private void setUser() {
        userViewModel.getUser(IdHolder.getInstance().getData()).observe(this,
                new Observer<User>() {
                    @Override
                    public void onChanged(User user) {
                        name.setText(user.getName());
                        description.setText(user.getDescription());
                        styles.setText(user.getClimbingTypes().toString());
                        image.setImageBitmap(user.getProfilePicture());
                    }
                });
    }

    private void setListeners() {
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SwipeActivity.class);
                startActivity(intent);
            }
        });

        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ContactsActivity.class);
                startActivity(intent);
            }
        });
    }
}