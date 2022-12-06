package com.diploma.climber.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.diploma.climber.enums.Regex;

import java.io.Serializable;

import kotlinx.parcelize.Parcelize;

@Entity(tableName = "accounts")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "userId")
    private int userId;

    public Account() {
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userId=" + userId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
/*        if (email.matches(Regex.EMAIL.getRegex())) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Wrong email provided");
        }*/
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
/*        if (password.matches(Regex.PASSWORD.getRegex())) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Wrong password provided");
        }*/
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
