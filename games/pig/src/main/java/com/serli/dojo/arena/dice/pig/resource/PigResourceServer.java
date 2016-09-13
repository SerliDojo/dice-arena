package com.serli.dojo.arena.dice.pig.resource;

import com.serli.dojo.arena.dice.resource.ResourceServer;

public class PigResourceServer {

	public static void main(String[] args) {
		ResourceServer.startResources("http://localhost:180", 
				PigGameResource.class,
				PigPlayerHoldingResource.class,
				PigPlayerHoldingAfterFifteenPointsEarnedResource.class,
				PigPlayerHoldingAfterFiveRollsResource.class);
	}

}
