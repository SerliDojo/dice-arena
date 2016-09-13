package com.serli.dojo.arena.dice.pig.resource;

import javax.ws.rs.Path;

import com.serli.dojo.arena.dice.pig.PigAction;
import com.serli.dojo.arena.dice.pig.PigMatch;
import com.serli.dojo.arena.dice.pig.PigPlayerHolding;
import com.serli.dojo.arena.dice.resource.PlayerResource;

@Path("/pig/holding")
public class PigPlayerHoldingResource extends PlayerResource<PigAction, PigMatch> {

	public PigPlayerHoldingResource() {
		super(new PigPlayerHolding("holding"));
	}

}
