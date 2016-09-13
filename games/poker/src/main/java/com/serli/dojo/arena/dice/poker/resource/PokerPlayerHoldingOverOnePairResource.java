package com.serli.dojo.arena.dice.poker.resource;

import javax.ws.rs.Path;

import com.serli.dojo.arena.dice.poker.PokerAction;
import com.serli.dojo.arena.dice.poker.PokerMatch;
import com.serli.dojo.arena.dice.poker.PokerPlayerHoldingOverOnePair;
import com.serli.dojo.arena.dice.resource.PlayerResource;

@Path("/poker/holding1pair")
public class PokerPlayerHoldingOverOnePairResource extends PlayerResource<PokerAction, PokerMatch> {

	public PokerPlayerHoldingOverOnePairResource() {
		super(new PokerPlayerHoldingOverOnePair("holding1pair"));
	}

}
