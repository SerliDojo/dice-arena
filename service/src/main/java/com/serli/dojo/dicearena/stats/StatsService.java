package com.serli.dojo.dicearena.stats;

public interface StatsService {

	void searchStats(String query);

	void searchStats(String query, int from);

	void getLastStats();

	void getLastStats(int from);

	void getStatsForPlayer(String name);

	void getStatsForPlayer(String name, int from);

	void getStatsForGame(String name);

	void getStatsForGame(String name, int from);

	void getStatsForMatch(String name);

	void getStatsForMatch(String name, int from);
}
