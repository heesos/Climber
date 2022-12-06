package com.diploma.climber;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.diploma.climber.DAO.BlockDAO;
import com.diploma.climber.blockchain.Block;
import com.diploma.climber.database.AppDatabase;
import com.diploma.climber.repository.BlockRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class BlockEntityTest {

    private BlockDAO blockDAO;
    private AppDatabase appDatabase;
    private BlockRepository blockRepository;

    @Before
    public void initDb() {
        Context context = ApplicationProvider.getApplicationContext();
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        blockDAO = appDatabase.blockDAO();
        blockRepository = new BlockRepository(blockDAO);
    }

    @After
    public void closeDb() {
        appDatabase.close();
    }


    @Test
    public void insert_test() {
        Block block1 = new Block("0", 1);
        Block block2 = new Block(block1.getHash(), 2);

        blockDAO.insertBlock(block1);
        blockDAO.insertBlock(block2);
    }

    @Test
    public void repository_insert_test() {
        Block initBlock = new Block("0", 1);

        blockRepository.insertBlock(1);

        blockRepository.insertBlock(2);

        List<Block> blockChain = blockDAO.getBlockchain();

        System.out.println(blockChain.get(0));
        System.out.println(blockChain.get(1));

    }
}
