package com.diploma.climber;

import com.diploma.climber.blockchain.Block;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)

public class BlockTest {

    private final String TAG = "BlockTest.class, block hash:";

    Block block0;
    Block block1;

    @Before
    public void initializeBlocks() {
        block0 = new Block("0", 1);
        block1 = new Block(block0.getHash(), 2);
    }

    @Test
    public void check_generated_hashes() {
        System.out.println(block0.getHash());
        System.out.println(block1.getHash());
    }
}
