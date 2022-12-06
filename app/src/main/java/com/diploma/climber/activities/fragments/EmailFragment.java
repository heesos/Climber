package com.diploma.climber.activities.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.diploma.climber.R;
import com.diploma.climber.activities.LoginActivity;
import com.diploma.climber.domain.Account;

public class EmailFragment extends Fragment {

    private Account account;
    private EditText editTextEmail;
    private Button button;
    private ImageButton imageButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_email, container, false);

        initializeClickable(view);
        setViewListeners(view);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initializeClickable(View view) {
        editTextEmail = view.findViewById(R.id.enterEmailRegister);
        button = view.findViewById(R.id.button2);
        imageButton = view.findViewById(R.id.imageButton);
        account = new Account(); //fresh account, where everything will be set
    }

    private void setViewListeners(View view) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account.setEmail(getEmail());
                EmailFragmentDirections.ActionEmailFragmentToPasswordFragment action = EmailFragmentDirections
                        .actionEmailFragmentToPasswordFragment(account);
                Navigation.findNavController(view).navigate(action);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }


    private String getEmail() {
        return editTextEmail.getText().toString();
    }
}