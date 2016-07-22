package com.serli.dojo.arena.dice;

public interface Player<A extends Action, M extends Match<A>> {

	String name();

	A play(M match);

}