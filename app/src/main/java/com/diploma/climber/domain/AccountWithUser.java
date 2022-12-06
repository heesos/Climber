package com.diploma.climber.domain;

import androidx.room.Embedded;
import androidx.room.Relation;

public class AccountWithUser {
    @Embedded
    private User user;
    @Relation(
            parentColumn = "id", // user's id
            entityColumn = "userId" // user's id declared in Account.class
    )
    private Account account;
    // @Relation annotation is responsible for creating relationships between
    // the tables

    public AccountWithUser() {
    }

    @Override
    public String toString() {
        return "AccountWithUser{" +
                "account=" + account +
                ", user=" + user +
                '}';
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
