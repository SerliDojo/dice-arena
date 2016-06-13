package com.serli.dojo.arena.dice;
import java.util.Random;


public class Die {

	private static final Random RANDOM = new Random();

	public static int roll() {
		return RANDOM.nextInt(6) + 1; 
	}
}
