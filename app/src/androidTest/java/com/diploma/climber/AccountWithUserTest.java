package com.diploma.climber;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.diploma.climber.DAO.AccountDAO;
import com.diploma.climber.DAO.UserDAO;
import com.diploma.climber.database.AppDatabase;
import com.diploma.climber.domain.Account;
import com.diploma.climber.domain.AccountWithUser;
import com.diploma.climber.domain.User;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AccountWithUserTest {

    private AppDatabase appDatabase;
    private UserDAO userDAO;
    private AccountDAO accountDAO;

    @Before
    public void openDb() {
        Context context = ApplicationProvider.getApplicationContext();
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        userDAO = appDatabase.userDAO();
        accountDAO = appDatabase.accountDAO();
    }

    @After
    public void closeDb() {
        appDatabase.close();
    }

    @Test
    public void getting_full_user_information() {
        User user = new User();
        Account account = new Account();

        user.setName("Milosz");
        account.setUserId(1);

        accountDAO.insertOne(account);
        userDAO.insertOne(user);


        AccountWithUser accountWithUser = userDAO.getFullUserInformation(1);

        Log.d("Full information", accountWithUser.toString());

        Assert.assertEquals(user.getName(), accountWithUser.getUser().getName());
        Assert.assertEquals(account.getUserId(), accountWithUser.getAccount().getUserId());

    }
}
