package com.diploma.climber.activities.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.diploma.climber.R;
import com.diploma.climber.database.AppDatabase;
import com.diploma.climber.domain.Account;
import com.diploma.climber.domain.User;
import com.diploma.climber.repository.BlockRepository;
import com.diploma.climber.viewModel.AccountViewModel;
import com.diploma.climber.viewModel.UserViewModel;

public class DescriptionFragment extends Fragment {

    private EditText editText;
    private EditText nameText;
    private Button nextButton;
    private Account account;
    private User user;
    private AccountViewModel accountViewModel;
    private UserViewModel userViewModel;
    private BlockRepository blockRepository;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_description, container, false);

        initializeVariables(view);
        setListeners(view);

        return view;
    }

    private void initializeVariables(View view) {
        editText = view.findViewById(R.id.descriptionText);
        nextButton = view.findViewById(R.id.button2);
        nameText = view.findViewById(R.id.nameText);
        account = DescriptionFragmentArgs.fromBundle(getArguments()).getAccount();
        user = DescriptionFragmentArgs.fromBundle(getArguments()).getUser();
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        blockRepository = new BlockRepository(AppDatabase.getInstance(view.getContext()).blockDAO());
    }

    private void setListeners(View view) {
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setDescription(getDescription());
                user.setName(getName());
                logAccountUser();

                addToDatabase(account, user);

                Navigation.findNavController(view).navigate(R.id.action_descriptionFragment_to_finishFragment);
            }
        });
    }

    private String getDescription() {
        return editText.getText().toString();
    }

    private String getName() {
        return nameText.getText().toString();
    }

    private void logAccountUser() {
        Log.d("Account:", account.toString());
        Log.d("User:", user.toString());
    }

    private void addToDatabase(Account account, User user) {
        long id = userViewModel.insertUser(user);
        account.setUserId((int) id);
        accountViewModel.insertAccount(account);
        blockRepository.insertBlock(id);
    }
}