package com.serli.dojo.arena.dice.pig.resource;

import javax.ws.rs.Path;

import com.serli.dojo.arena.dice.pig.PigAction;
import com.serli.dojo.arena.dice.pig.PigMatch;
import com.serli.dojo.arena.dice.pig.PigPlayerHoldingAfterFifteenPointsEarned;
import com.serli.dojo.arena.dice.resource.PlayerResource;

@Path("/pig/holding15points")
public class PigPlayerHoldingAfterFifteenPointsEarnedResource extends PlayerResource<PigAction, PigMatch> {

	public PigPlayerHoldingAfterFifteenPointsEarnedResource() {
		super(new PigPlayerHoldingAfterFifteenPointsEarned("holding15points"));
	}

}
