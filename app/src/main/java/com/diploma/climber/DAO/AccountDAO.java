package com.diploma.climber.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.diploma.climber.domain.Account;

import java.util.List;

@Dao
public interface AccountDAO extends BasicDAO<Account> {
    @Query("SELECT * FROM accounts")
    LiveData<List<Account>> getAccounts();

    @Query("SELECT * FROM accounts WHERE id = :id")
    Account getAccountById(int id);

    @Query("SELECT email FROM accounts WHERE userId = :id")
    String getEmailById(int id);

    @Query("DELETE FROM accounts WHERE id = :id")
    void deleteAccountById(int id);

    @Query("DELETE FROM users WHERE id = :id")
    void deleteUserById(int id);


    //we don't use LiveData here because this is used to check wheter the user can log in
    //therefore we allow to use main Thread and a little bit of wait time to execute
    @Query("SELECT * FROM accounts WHERE email = :email")
    Account getAccountByEmail(String email);
}
