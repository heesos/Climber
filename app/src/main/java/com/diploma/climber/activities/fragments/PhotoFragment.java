package com.diploma.climber.activities.fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.diploma.climber.R;
import com.diploma.climber.domain.Account;
import com.diploma.climber.domain.User;

import java.io.IOException;


public class PhotoFragment extends Fragment {

    private ImageView imageView;
    private Bitmap userPhoto;
    private Button nextButton;
    private Account account;
    private User user;
    private ActivityResultLauncher<Intent> activityResultLauncher;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_photo, container, false);

        initializeVariables(view);
        setListeners(view);

        return view;
    }

    private void initializeVariables(View view) {
        nextButton = view.findViewById(R.id.button2);
        imageView = view.findViewById(R.id.imageView);
        user = new User();
        account = PhotoFragmentArgs.fromBundle(getArguments()).getAccount();
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        Uri uri = data.getData();
                        try {
                            userPhoto = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                            imageView.setImageBitmap(userPhoto);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    private void setListeners(View view) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                activityResultLauncher.launch(intent);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setProfilePicture(getUserPhoto());

                PhotoFragmentDirections.ActionPhotoFragmentToClimbTypesFragment action = PhotoFragmentDirections
                        .actionPhotoFragmentToClimbTypesFragment(account,user);

                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    private Bitmap getUserPhoto() {
        return userPhoto;
    }
}