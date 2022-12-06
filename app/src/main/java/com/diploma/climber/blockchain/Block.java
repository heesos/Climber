package com.diploma.climber.blockchain;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Date;
import java.util.List;

/**
 * Class which is used as blockchain blocks.
 * These block will be fulfilled with user's account id
 *
 * @see <a href="https://www.baeldung.com/java-blockchain">Baeldung - Java Blockchain</a>
 */

@Entity(tableName = "blocks")
public class Block implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * Contains:
     *
     * @param hash the hash value of the current block
     * @param previousHash the hash value of the previous block
     * @param data user's account_id to be stored
     * @param timeStamp The timestamp of the creation of this block
     * @param nonce arbitrary number used in cryptography
     */

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String hash;
    private String previousHash;
    private long data;
    private long timeStamp;
    //private int nonce;

    public Block(String previousHash, long data) {
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = new Date().getTime();
        this.hash = calculateBlockHash();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }


    public long getTimeStamp() {
        return timeStamp;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    private String calculateBlockHash() {
        String input = previousHash + timeStamp + data;
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            int i = 0;

            byte[] hash = sha.digest(input.getBytes("UTF-8"));

            // hexHash will contain
            // the Hexadecimal hash
            StringBuffer hexHash = new StringBuffer();

            while (i < hash.length) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexHash.append('0');
                hexHash.append(hex);
                i++;
            }

            return hexHash.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Boolean isChainValid(List<Block> blockchain) {
        Block currentBlock;
        Block previousBlock;

        // Iterating through
        // all the blocks
        for (int i = 1; i < blockchain.size(); i++) {

            // Storing the current block
            // and the previous block
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);

            // Checking if the current hash
            // is equal to the
            // calculated hash or not
            if (!currentBlock.hash.equals(currentBlock.calculateBlockHash())) {
                System.out.println("Hashes are not equal");
                return false;
            }

            // Checking of the previous hash
            // is equal to the calculated
            // previous hash or not
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous Hashes are not equal");
                return false;
            }
        }

        // If all the hashes are equal
        // to the calculated hashes,
        // then the blockchain is valid
        return true;
    }

    @Override
    public String toString() {
        return "Block{" +
                "hash='" + hash + '\'' +
                ", previousHash='" + previousHash + '\'' +
                ", data=" + data +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
