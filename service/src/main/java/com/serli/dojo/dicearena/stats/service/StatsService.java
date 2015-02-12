package com.serli.dojo.dicearena.stats.service;

import java.util.List;

import com.serli.dojo.dicearena.stats.model.Game;
import com.serli.dojo.dicearena.stats.model.Match;
import com.serli.dojo.dicearena.stats.model.Player;
import com.serli.dojo.dicearena.stats.model.Stat;

public interface StatsService {

	List<Stat> searchStats(String query);

	List<Stat> searchStats(String query, int from);

	List<Stat> getLastStats();

	List<Stat> getLastStats(int from);

	List<Stat> getStatsForPlayer(Player player);

	List<Stat> getStatsForPlayer(Player player, int from);

	List<Stat> getStatsForGame(Game game);

	List<Stat> getStatsForGame(Game game, int from);

	List<Stat> getStatsForMatch(Match match);

	List<Stat> getStatsForMatch(Match match, int from);
}
