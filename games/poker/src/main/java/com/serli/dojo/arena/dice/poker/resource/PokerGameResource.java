package com.serli.dojo.arena.dice.poker.resource;

import javax.ws.rs.Path;

import com.serli.dojo.arena.dice.poker.PokerAction;
import com.serli.dojo.arena.dice.poker.PokerGame;
import com.serli.dojo.arena.dice.poker.PokerMatch;
import com.serli.dojo.arena.dice.resource.GameResource;

@Path("/poker")
public class PokerGameResource extends GameResource<PokerAction, PokerMatch> {

	public PokerGameResource() {
		super(new PokerGame("POKER"));
	}

}
