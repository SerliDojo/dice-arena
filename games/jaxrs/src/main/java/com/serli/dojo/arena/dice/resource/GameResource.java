package com.serli.dojo.arena.dice.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;

import com.serli.dojo.arena.dice.Action;
import com.serli.dojo.arena.dice.Game;
import com.serli.dojo.arena.dice.Match;

@Consumes("application/json")
@Produces("application/json")
public abstract class GameResource<A extends Action, M extends Match<A>> {

	public static class Request<A extends Action, M extends Match<A>> {
		public A action;
		public M match;
	}

	private Game<A, M> game;

	public GameResource(Game<A, M> game) {
		this.game = game;
	}

	@GET
	public String getName() {
		return game.getName();
	}

	@POST
	public M init(List<String> players) {
		return game.init(players);
	}

	@PUT
	public M apply(Request<A, M> request) {
		return game.apply(request.action, request.match);
	}

}
