package com.diploma.climber.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.diploma.climber.R;
import com.diploma.climber.database.DatabaseInitializer;
import com.diploma.climber.domain.Account;
import com.diploma.climber.domain.IdHolder;
import com.diploma.climber.domain.User;
import com.diploma.climber.viewModel.AccountViewModel;
import com.diploma.climber.viewModel.UserViewModel;

import java.util.List;

public class LoginActivity extends AppCompatActivity {


    private EditText emailText, passwordText;
    private Button loginButton, registerButton;
    private AccountViewModel accountViewModel;
    private UserViewModel userViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeVariable();
        setListeners();
        //initDatabase(5);
    }

    private boolean login(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Empty text fields", Toast.LENGTH_LONG).show();
            return false;
        } else {
            Account account = accountViewModel.getAccountByEmail(email);

            if (account == null) {
                Toast.makeText(this, "Wrong email", Toast.LENGTH_LONG).show();
                return false;
            } else if (!account.getPassword().equals(password)) {
                Toast.makeText(this, "Wrong password", Toast.LENGTH_LONG).show();
                return false;
            } else {
                IdHolder.getInstance().setId(account.getUserId());
                return true;
            }
        }
    }

    private void initializeVariable() {
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        emailText = findViewById(R.id.editTextTextPersonName);
        passwordText = findViewById(R.id.editTextTextPassword);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
    }

    private void setListeners() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValidUser;
                isValidUser = login(emailText.getText().toString(), passwordText.getText().toString());
                if (isValidUser) {
                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(intent);
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initDatabase(int numberOfAccounts) {
        DatabaseInitializer databaseInitializer = new DatabaseInitializer(numberOfAccounts);
        List<Account> accountList = databaseInitializer.initAccounts();
        List<User> userList = databaseInitializer.initUsers();

        for (int i = 0; i < accountList.size(); i++) {
            userViewModel.insertUser(userList.get(i));
            accountViewModel.insertAccount(accountList.get(i));
        }
    }


    //TODO :
    //  https://www.youtube.com/watch?v=SJW_4UMXbu8
    // make swipe cards
    // fetch all then minus the user itself so it wont show as a card
    // if yes go with UserWithOthers.Class (check tests to see how does it work)
    // if no then idk, just to to another class
    // ezzz
}