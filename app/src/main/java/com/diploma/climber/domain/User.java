package com.diploma.climber.domain;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

/**
 * Basic user entity used for storing user's pieces of information.
 * @author mmarzec
 */
@Entity(tableName = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "profile_picture")
    private Bitmap profilePicture;

    @ColumnInfo(name = "climbing_type")
    private List<String> climbingTypes;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", profilePicture=" + profilePicture +
                ", climbingTypes=" + climbingTypes +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Bitmap profilePicture) {
        this.profilePicture = profilePicture;
    }

    public List<String> getClimbingTypes() {
        return climbingTypes;
    }

    public void setClimbingTypes(List<String> climbingTypes) {
        this.climbingTypes = climbingTypes;
    }
}
