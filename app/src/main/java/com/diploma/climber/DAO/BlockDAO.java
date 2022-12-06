package com.diploma.climber.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.diploma.climber.blockchain.Block;

import java.util.List;

@Dao
public interface BlockDAO {
    @Insert
    void insertBlock(Block block);

    @Query("SELECT * FROM blocks")
    List<Block> getBlockchain();

    @Query("SELECT last_insert_rowid() FROM blocks")
    int getLastInsertedId();

    @Query("SELECT hash FROM blocks WHERE id= :id")
    String getLastInsertedHash(int id);
}
