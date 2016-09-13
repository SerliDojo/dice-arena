package com.serli.dojo.arena.dice.poker.resource;

import javax.ws.rs.Path;

import com.serli.dojo.arena.dice.poker.PokerAction;
import com.serli.dojo.arena.dice.poker.PokerMatch;
import com.serli.dojo.arena.dice.poker.PokerPlayerRolling;
import com.serli.dojo.arena.dice.resource.PlayerResource;

@Path("/poker/rolling")
public class PokerPlayerRollingResource extends PlayerResource<PokerAction, PokerMatch> {

	public PokerPlayerRollingResource() {
		super(new PokerPlayerRolling("rolling"));
	}

}
