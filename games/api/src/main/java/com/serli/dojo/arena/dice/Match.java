package com.serli.dojo.arena.dice;

public interface Match<A extends Action> {

	boolean isFinished();

	<M extends Match<A>, P extends Player<A, M>> P currentPlayer();

}