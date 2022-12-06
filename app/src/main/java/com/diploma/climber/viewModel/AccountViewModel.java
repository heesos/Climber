package com.diploma.climber.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.diploma.climber.DAO.AccountDAO;
import com.diploma.climber.database.AppDatabase;
import com.diploma.climber.domain.Account;
import com.diploma.climber.repository.BlockRepository;

import java.util.List;

public class AccountViewModel extends AndroidViewModel {

    AccountDAO accountDAO;
    BlockRepository blockRepository;
    String blockTAG = "Block";

    public AccountViewModel(@NonNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        this.accountDAO = appDatabase.accountDAO();
        this.blockRepository = new BlockRepository(appDatabase.blockDAO());
    }

    public Account getAccountByEmail(String email) {
        return accountDAO.getAccountByEmail(email);
    }

    public LiveData<List<Account>> getAllAccounts() {
        return accountDAO.getAccounts();
    }

    public long insertAccount(Account account) {
//        AppDatabase.dataBaseWriteExecutor.execute(() -> {
        long id = accountDAO.insertOne(account);
        return id;
        // });

    }

    public void updateAccount(Account account) {
        AppDatabase.dataBaseWriteExecutor.execute(() -> {
            accountDAO.update(account);
        });
    }

    public String getEmailByUserId(int id) {
        return accountDAO.getEmailById(id);
    }
}
