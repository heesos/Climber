package com.diploma.climber.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.diploma.climber.DAO.UserDAO;
import com.diploma.climber.database.AppDatabase;
import com.diploma.climber.domain.AccountWithUser;
import com.diploma.climber.domain.User;
import com.diploma.climber.domain.usersRelations.UserUserCrossEntity;
import com.diploma.climber.domain.usersRelations.UserWithOthers;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    UserDAO userDAO;

    public UserViewModel(@NonNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        userDAO = appDatabase.userDAO();
    }

    public long insertUser(User user) {
        final long id;
        id = userDAO.insertOne(user);
        return id;
    }

    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    public List<User> getAllUsers() {
        return userDAO.getUsers();
    }

    public void insertUserRelations(UserUserCrossEntity userUserCrossEntity) {
        userDAO.insertUserRelation(userUserCrossEntity);
    }

    public UserWithOthers getUserRelations(int id) {
        return userDAO.getUserRelations(id);
    }

    public AccountWithUser getFullUserInformation(int id) {
        return userDAO.getFullUserInformation(id);
    }

    public LiveData<User> getUser(int id) {
        return userDAO.getUserByIdLiveData(id);
    }
}
