package com.diploma.climber.domain.usersRelations;

import androidx.room.Entity;

@Entity(primaryKeys = {"firstUserId", "secondUserId"})
public class UserUserCrossEntity {
    private int firstUserId;
    private int secondUserId;

    public UserUserCrossEntity() {
    }

    @Override
    public String toString() {
        return "UserUserCrossEntity{" +
                "firstUserId=" + firstUserId +
                ", secondUserId=" + secondUserId +
                '}';
    }

    public int getFirstUserId() {
        return firstUserId;
    }

    public void setFirstUserId(int firstUserId) {
        this.firstUserId = firstUserId;
    }

    public int getSecondUserId() {
        return secondUserId;
    }

    public void setSecondUserId(int secondUserId) {
        this.secondUserId = secondUserId;
    }
}
