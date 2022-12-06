package com.diploma.climber;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.diploma.climber.DAO.UserDAO;
import com.diploma.climber.database.AppDatabase;
import com.diploma.climber.domain.User;
import com.diploma.climber.domain.usersRelations.UserUserCrossEntity;
import com.diploma.climber.domain.usersRelations.UserWithOthers;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class UserRelationsTest {

    private AppDatabase appDatabase;
    private UserDAO userDAO;

    @Before
    public void openDb() {
        Context context = ApplicationProvider.getApplicationContext();
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        userDAO = appDatabase.userDAO();
    }

    @After
    public void closeDb() {
        appDatabase.clearAllTables();
        appDatabase.close();
    }

    @Test
    public void test() {
        User user = new User();
        User secondUser = new User();

        user.setName("Name");
        secondUser.setName("Second name");

        user.setDescription("Test");
        secondUser.setDescription("TEST DESC");

        user.setId(1);
        secondUser.setId(2);


        userDAO.insertAll(user, secondUser);

        UserUserCrossEntity userUserCrossEntity = new UserUserCrossEntity();
        userUserCrossEntity.setFirstUserId(user.getId());
        userUserCrossEntity.setSecondUserId(secondUser.getId());

        userDAO.insertUserRelation(userUserCrossEntity);

        UserWithOthers userWithOthers= userDAO.getUserRelations(user.getId());

        Assert.assertEquals(secondUser.getId(), userWithOthers.otherUsers.get(0).getId());
        Assert.assertEquals(user.getId(), userWithOthers.user.getId());

        Log.d("User's relations ", String.valueOf(userWithOthers));
    }
}
