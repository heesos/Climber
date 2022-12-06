package com.diploma.climber;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.diploma.climber.DAO.UserDAO;
import com.diploma.climber.database.AppDatabase;
import com.diploma.climber.domain.AccountWithUser;
import com.diploma.climber.domain.User;
import com.diploma.climber.enums.ClimbingTypes;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;


@RunWith(AndroidJUnit4.class)
public class UserTest {
   private UserDAO userDao;
   private AppDatabase db;

    @Before
    public void openDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        userDao = db.userDAO();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void insert_and_read_user_test() {
        User user = new User();
        List<String> climbingTypes = List.of(ClimbingTypes.SPORT.getName(), ClimbingTypes.SUICIDE.getName());
        user.setName("Milosz");
        user.setDescription("Hi my name is Milosz");
        user.setClimbingTypes(climbingTypes);

        long id = userDao.insertOne(user);

        User userFromDb = userDao.getUserById(1);

        Assert.assertEquals(user.getName(), userFromDb.getName());
        Assert.assertEquals(user.getDescription(), userFromDb.getDescription());
        Assert.assertEquals(1, userFromDb.getId());
        Assert.assertEquals(climbingTypes.size(), userFromDb.getClimbingTypes().size());
        Assert.assertEquals(id, 1);
        Log.d("User info", userFromDb.toString());
    }
}
