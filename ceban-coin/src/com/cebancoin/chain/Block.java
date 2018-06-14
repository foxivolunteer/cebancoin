package com.cebancoin.chain;

import java.util.ArrayList;
import java.util.Date;

import com.cebancoin.crypto.DigitalSignature;
import com.cebancoin.service.Transaction;
 
public class Block {
	 
	//block signature 
	public String hash;
	//previous block signature
	public String previousHash;
	//block data
	private String data;
	//time in milliseconds
	private long timeStamp;
	private int nonce;
	public String merkleRoot;
	//our data will be a simple message.
	public ArrayList<Transaction> transactions = new ArrayList<Transaction>(); 
	
	//Block Constructor.  
	public Block(String previousHash ) {
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		
		this.hash = calculateHash(); //Making sure we do this after we set the other values.
	}
	
	//Calculate new hash based on blocks contents
	public String calculateHash() {
		String calculatedhash = DigitalSignature.encyriptSha256( 
				previousHash +
				Long.toString(timeStamp) +
				Integer.toString(nonce) + 
				merkleRoot
				);
		return calculatedhash;
	}
	
	//Increases nonce value until hash target is reached.
	public void mineBlock(int difficulty) {
		merkleRoot = DigitalSignature.getMerkleRoot(transactions);
		String target = DigitalSignature.getDificultyString(difficulty); //Create a string with difficulty * "0" 
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}
	
	//Add transactions to this block
	public boolean addTransaction(Transaction transaction) {
		//process transaction and check if valid, unless block is genesis block then ignore.
		if(transaction == null) return false;		
		if((previousHash != "0")) {
			if((transaction.processTransaction() != true)) {
				System.out.println("Transaction failed to process. Discarded.");
				return false;
			}
		}
		transactions.add(transaction);
		System.out.println("Transaction Successfully added to Block");
		return true;
}
}
