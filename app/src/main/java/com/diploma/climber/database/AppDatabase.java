package com.diploma.climber.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.diploma.climber.DAO.AccountDAO;
import com.diploma.climber.DAO.BlockDAO;
import com.diploma.climber.DAO.UserDAO;
import com.diploma.climber.blockchain.Block;
import com.diploma.climber.database.converter.ImageBitmapString;
import com.diploma.climber.database.converter.StringList;
import com.diploma.climber.domain.Account;
import com.diploma.climber.domain.User;
import com.diploma.climber.domain.usersRelations.UserUserCrossEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kotlin.jvm.Volatile;

@Database(entities = {User.class, Account.class, UserUserCrossEntity.class, Block.class}, version = 5, exportSchema = true)
@TypeConverters({ImageBitmapString.class, StringList.class})
public abstract class AppDatabase extends RoomDatabase {
    private static final String TAG = "AppDatabase";
    private static final String DB_NAME = "climber.db";
    private static final int NUMBER_OF_THREADS = 4;
    @Volatile // make it visible to all threads
    private static AppDatabase instance;
    public static final ExecutorService dataBaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,
                    DB_NAME).allowMainThreadQueries().build();

        }
        return instance;
    }



    public abstract UserDAO userDAO();
    public abstract AccountDAO accountDAO();
    public abstract BlockDAO blockDAO();


}
