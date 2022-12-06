package com.diploma.climber;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.diploma.climber.DAO.AccountDAO;
import com.diploma.climber.database.AppDatabase;
import com.diploma.climber.domain.Account;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)

public class AccountTest {

    private AccountDAO accountDAO;
    private AppDatabase appDatabase;

    @Before
    public void initDb() {
        Context context = ApplicationProvider.getApplicationContext();
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        accountDAO = appDatabase.accountDAO();
    }

    @After
    public void closeDb() {
        appDatabase.close();
    }

    @Test
    public void insert_and_read_account_test() {
        Account account = new Account();
        account.setEmail("email@email.com");
        account.setPassword("Password@123");
        account.setUserId(1);

        accountDAO.insertOne(account);

        Account accountFromDb = accountDAO.getAccountById(1);


        Assert.assertEquals(account.getEmail(), accountFromDb.getEmail());
        Assert.assertEquals(account.getPassword(), accountFromDb.getPassword());
        Assert.assertEquals(1, accountFromDb.getId());
        Assert.assertEquals(account.getUserId(), accountFromDb.getUserId());

        Log.d("Account information", accountFromDb.toString());
    }
}

