package com.serli.dojo.arena.dice.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

import com.serli.dojo.arena.dice.Action;
import com.serli.dojo.arena.dice.Match;
import com.serli.dojo.arena.dice.Player;

@Consumes("application/json")
@Produces("application/json")
public abstract class PlayerResource<A extends Action, M extends Match<A>> {

	private Player<A, M> player;

	public PlayerResource(Player<A, M> player) {
		this.player = player;
	}

	@GET
	public String getName() {
		return player.getName();
	}

	@POST
	public A apply(M match) {
		return player.play(match);
	}

}
