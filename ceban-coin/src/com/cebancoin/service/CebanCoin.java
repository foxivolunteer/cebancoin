package com.cebancoin.service;

import java.util.ArrayList;

import com.cebancoin.chain.Block;
import com.google.gson.GsonBuilder;
 
public class CebanCoin {
	// block chain list 
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
  
	public static void main(String[] args) {
		// add blocks to the blockchain ArrayList:
		blockchain.add(new Block("first block", "0"));
		blockchain.add(new Block("second block", blockchain.get(blockchain.size() - 1).hash));
		blockchain.add(new Block("third block", blockchain.get(blockchain.size() - 1).hash));
		//gson from google gson-2.6.2.jar lib.
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println(blockchainJson);
	}
}
