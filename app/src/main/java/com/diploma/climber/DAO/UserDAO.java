package com.diploma.climber.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.diploma.climber.domain.AccountWithUser;
import com.diploma.climber.domain.User;
import com.diploma.climber.domain.usersRelations.UserUserCrossEntity;
import com.diploma.climber.domain.usersRelations.UserWithOthers;

import java.util.List;

@Dao
public interface UserDAO extends BasicDAO<User> {
    @Query("SELECT * FROM users")
    List<User> getUsers();

    @Query("SELECT * FROM users WHERE id = :id")
    User getUserById(int id);

    @Query("SELECT * FROM users WHERE id = :id")
    LiveData<User> getUserByIdLiveData(int id);

    @Transaction
    @Query("SELECT * from users WHERE id = :id")
    AccountWithUser getFullUserInformation(int id);


    @Transaction
    @Query("SELECT * FROM users WHERE id = :id")
    UserWithOthers getUserRelations(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUserRelation(UserUserCrossEntity userUserCrossEntity);
}
