package com.diploma.climber.activities.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.diploma.climber.R;
import com.diploma.climber.domain.Account;


public class PasswordFragment extends Fragment {

    private ImageButton imageButton;
    private Button button;
    private Account account;
    private EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_password, container, false);

        initializeVariables(view);
        setListeners(view);

        return view;
    }

    private void initializeVariables(View view) {
        editText = view.findViewById(R.id.enterPasswordRegister);
        button = view.findViewById(R.id.buttonPassword);
        account = PasswordFragmentArgs.fromBundle(getArguments()).getAccount();
        imageButton = view.findViewById(R.id.imageButton);
    }

    private void setListeners(View view) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account.setPassword(getPassword());
                PasswordFragmentDirections.ActionPasswordFragmentToPhotoFragment action = PasswordFragmentDirections.actionPasswordFragmentToPhotoFragment(account);
                Navigation.findNavController(view).navigate(action);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_passwordFragment_to_emailFragment);
            }
        });
    }

    private String getPassword() {
        return this.editText.getText().toString();
    }
}