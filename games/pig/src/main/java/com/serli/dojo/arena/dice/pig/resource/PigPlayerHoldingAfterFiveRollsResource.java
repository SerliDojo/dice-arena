package com.serli.dojo.arena.dice.pig.resource;

import javax.ws.rs.Path;

import com.serli.dojo.arena.dice.pig.PigAction;
import com.serli.dojo.arena.dice.pig.PigMatch;
import com.serli.dojo.arena.dice.pig.PigPlayerHoldingAfterFiveRolls;
import com.serli.dojo.arena.dice.resource.PlayerResource;

@Path("/pig/holding5rolls")
public class PigPlayerHoldingAfterFiveRollsResource extends PlayerResource<PigAction, PigMatch> {

	public PigPlayerHoldingAfterFiveRollsResource() {
		super(new PigPlayerHoldingAfterFiveRolls("holding5rolls"));
	}

}
