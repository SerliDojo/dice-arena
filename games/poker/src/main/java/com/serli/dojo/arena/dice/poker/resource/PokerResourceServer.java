package com.serli.dojo.arena.dice.poker.resource;

import com.serli.dojo.arena.dice.resource.ResourceServer;

public class PokerResourceServer {

	public static void main(String[] args) {
		ResourceServer.startResources("http://localhost:280", 
				PokerGameResource.class,
				PokerPlayerHoldingResource.class,
				PokerPlayerHoldingOverOnePairResource.class,
				PokerPlayerRollingResource.class);
	}

}
