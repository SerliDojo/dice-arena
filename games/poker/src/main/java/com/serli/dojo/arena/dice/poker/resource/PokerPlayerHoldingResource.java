package com.serli.dojo.arena.dice.poker.resource;

import javax.ws.rs.Path;

import com.serli.dojo.arena.dice.poker.PokerAction;
import com.serli.dojo.arena.dice.poker.PokerMatch;
import com.serli.dojo.arena.dice.poker.PokerPlayerHolding;
import com.serli.dojo.arena.dice.resource.PlayerResource;

@Path("/poker/holding")
public class PokerPlayerHoldingResource extends PlayerResource<PokerAction, PokerMatch> {

	public PokerPlayerHoldingResource() {
		super(new PokerPlayerHolding("holding"));
	}

}
