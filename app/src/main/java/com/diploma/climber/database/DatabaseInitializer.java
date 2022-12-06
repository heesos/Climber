package com.diploma.climber.database;

import android.graphics.Bitmap;
import android.util.Log;

import com.diploma.climber.database.converter.ImageBitmapString;
import com.diploma.climber.domain.Account;
import com.diploma.climber.domain.User;
import com.diploma.climber.enums.ClimbingTypes;
import com.diploma.climber.enums.PhotosEncoded;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class DatabaseInitializer {

    private static final String TAG = "DatabaseInitializer";

    private final int numberOfAccounts;

    public DatabaseInitializer(int numberOfAccounts) {
        this.numberOfAccounts = numberOfAccounts;
    }

    public List<User> initUsers() {
        Log.d(TAG, "starting users initialization");

        List<User> userList = new ArrayList<>();
        Bitmap bitmap = initImages();

        for (int i = 0; i < numberOfAccounts; i++) {
            User user = new User();
            user.setName(generateString());
            user.setDescription(generateString());
            user.setClimbingTypes(List.of(ClimbingTypes.SUICIDE.getName(), ClimbingTypes.SPORT.getName()));
            user.setProfilePicture(bitmap);
            userList.add(user);
        }

        Log.d(TAG, "finished users initialization");
        return userList;
    }

    public List<Account> initAccounts() {
        Log.d(TAG, "starting accounts initialization");

        List<Account> accountList = new ArrayList<>();

        for (int i = 0; i < numberOfAccounts; i++) {
            Account account = new Account();
            account.setUserId(i + 1);
            account.setEmail(generateEmail());
            account.setPassword(generateString());

            accountList.add(account);
        }

        Log.d(TAG, "finished accounts initialization");
        return accountList;
    }

    private Bitmap initImages() {
        Log.d(TAG, "starting bitmap initialization");

        Bitmap bitmap = ImageBitmapString.StringToBitmap(PhotosEncoded.PHOTO_ONE.getValue());

        Log.d(TAG, "finished bitmap initialization");
        return bitmap;
    }

    private String generateEmail() {
        String DOMAIN = "@gmail.com";
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 4;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString + DOMAIN;
    }

    private String generateString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }

        return buffer.toString();
    }

}
