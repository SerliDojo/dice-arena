package com.serli.dojo.arena.dice;

public interface Player<A extends Action, S extends State> {

	A play(S state);

}