package com.serli.dojo.arena.dice.pig.resource;

import javax.ws.rs.Path;

import com.serli.dojo.arena.dice.pig.PigAction;
import com.serli.dojo.arena.dice.pig.PigGame;
import com.serli.dojo.arena.dice.pig.PigMatch;
import com.serli.dojo.arena.dice.resource.GameResource;

@Path("/pig")
public class PigGameResource extends GameResource<PigAction, PigMatch> {

	public PigGameResource() {
		super(new PigGame("PIG"));
	}

}
