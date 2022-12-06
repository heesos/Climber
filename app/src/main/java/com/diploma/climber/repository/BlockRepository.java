package com.diploma.climber.repository;

import android.util.Log;

import com.diploma.climber.DAO.BlockDAO;
import com.diploma.climber.blockchain.Block;

import java.util.List;

public class BlockRepository {

    private BlockDAO blockDAO;

    public BlockRepository(BlockDAO blockDAO) {
        this.blockDAO = blockDAO;
    }

    public boolean insertBlock(long accountID) {
        String previousHash;
        Block block;

        List<Block> blockChain = blockDAO.getBlockchain();
        if(blockChain.isEmpty()) {
            block = new Block("0", accountID);
        } else {
            previousHash = blockChain.get(blockChain.size() - 1).getHash();
            block = new Block(previousHash, accountID);
        }
        blockChain.add(block);

        boolean isValid = Block.isChainValid(blockChain);

        if (isValid) {
            blockDAO.insertBlock(block);
            Log.d("Blockchain", "Blockchain is valid");
        }

        return isValid;
    }

}
