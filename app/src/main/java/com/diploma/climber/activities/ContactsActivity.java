package com.diploma.climber.activities;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.diploma.climber.R;
import com.diploma.climber.adapter.RecyclerViewAdapter;
import com.diploma.climber.domain.AccountWithUser;
import com.diploma.climber.domain.IdHolder;
import com.diploma.climber.domain.User;
import com.diploma.climber.viewModel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private List<AccountWithUser> contacts;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        initializeVariables();
        contacts = fetchContacts();

        initRecyclerView();

    }

    private void initializeVariables() {
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<AccountWithUser> fetchContacts() {

        int loggedId = IdHolder.getInstance().getData();

        List<AccountWithUser> accountWithUsers = new ArrayList<>();

        List<User> userList = userViewModel.getUserRelations(loggedId).otherUsers;

        userList.stream().forEach(user -> {
            accountWithUsers.add(userViewModel.getFullUserInformation(user.getId()));
        });

        return accountWithUsers;
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(contacts, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}