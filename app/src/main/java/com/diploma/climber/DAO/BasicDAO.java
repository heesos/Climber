package com.diploma.climber.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

@Dao
public interface BasicDAO<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(T... t);

    @Insert
    long insertOne(T t);

    @Delete
    void delete(T t);

    @Update
    void update (T t);
}
