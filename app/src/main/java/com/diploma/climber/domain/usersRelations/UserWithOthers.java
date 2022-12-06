package com.diploma.climber.domain.usersRelations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.diploma.climber.domain.User;

import java.util.List;

public class UserWithOthers {
    @Embedded
    public User user;
    @Relation(
            parentColumn = "id",
            entity = User.class,
            entityColumn = "id",
            associateBy = @Junction(value = UserUserCrossEntity.class,
            parentColumn = "firstUserId",
            entityColumn = "secondUserId"
            )
    )
    public List<User> otherUsers;

    public UserWithOthers() {
    }

    @Override
    public String toString() {
        return "UserWithOthers{" +
                "user=" + user +
                ", otherUsers=" + otherUsers +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getOtherUsers() {
        return otherUsers;
    }

    public void setOtherUsers(List<User> otherUsers) {
        this.otherUsers = otherUsers;
    }
}
