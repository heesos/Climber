package com.diploma.climber.activities.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.diploma.climber.R;
import com.diploma.climber.domain.Account;
import com.diploma.climber.domain.User;
import com.diploma.climber.enums.ClimbingTypes;

import java.util.ArrayList;
import java.util.List;

public class ClimbTypesFragment extends Fragment {

    private List<String> climbingTypes = new ArrayList<>();
    private Button nextButton;
    private CheckBox boulderingBox;
    private CheckBox sportBox;
    private CheckBox tradBox;
    private CheckBox speedBox;
    private CheckBox soloBox;
    private Account account;
    private User user;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_climb_types, container, false);

        initializeVariables(view);
        setListeners(view);

        return view;
    }

    private void initializeVariables(View view) {
        nextButton = view.findViewById(R.id.button2);
        boulderingBox = view.findViewById(R.id.bouldering);
        sportBox = view.findViewById(R.id.sport);
        tradBox = view.findViewById(R.id.trad);
        speedBox = view.findViewById(R.id.speed);
        soloBox = view.findViewById(R.id.suicide);
        account = ClimbTypesFragmentArgs.fromBundle(getArguments()).getAccount();
        user = ClimbTypesFragmentArgs.fromBundle(getArguments()).getUser();
    }

    private void setListeners(View view) {
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfBoxesChecked();
                user.setClimbingTypes(climbingTypes);
                ClimbTypesFragmentDirections.ActionClimbTypesFragmentToDescriptionFragment action = ClimbTypesFragmentDirections
                        .actionClimbTypesFragmentToDescriptionFragment(account, user);
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    private void checkIfBoxesChecked() {
        if (boulderingBox.isChecked()) {
            climbingTypes.add(ClimbingTypes.BOULDERING.getName());
        }
        if (sportBox.isChecked()) {
            climbingTypes.add(ClimbingTypes.SPORT.getName());
        }
        if (tradBox.isChecked()) {
            climbingTypes.add(ClimbingTypes.TRAD.getName());
        }
        if (speedBox.isChecked()) {
            climbingTypes.add(ClimbingTypes.SPEED.getName());
        }
        if (soloBox.isChecked()) {
            climbingTypes.add(ClimbingTypes.SUICIDE.getName());
        }
    }
}