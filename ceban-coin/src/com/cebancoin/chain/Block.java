package com.cebancoin.chain;

import java.util.Date;

import com.cebancoin.crypto.DigitalSignature;
 
public class Block {
	 
	//block signature
	public String hash;
	//previous block signature
	public String previousHash;
	//block data
	private String data;
	//time in milliseconds
	private long timeStamp;
	
	public Block(String hash, String previousHash) {
		super();
		this.hash = calculateHash();
		this.previousHash = previousHash;
		this.setData(data);
		this.setTimeStamp(new Date().getTime());
	}
	
	public String calculateHash() {
		String applyText = previousHash + Long.toString(timeStamp) + data;
		String calculatedhash = DigitalSignature.encyriptSha256(applyText);
		return calculatedhash;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
}
