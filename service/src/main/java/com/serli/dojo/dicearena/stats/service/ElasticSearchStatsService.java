package com.serli.dojo.dicearena.stats.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;

import com.serli.dojo.dicearena.stats.model.Game;
import com.serli.dojo.dicearena.stats.model.Match;
import com.serli.dojo.dicearena.stats.model.Player;
import com.serli.dojo.dicearena.stats.model.Score;
import com.serli.dojo.dicearena.stats.model.Stat;

public class ElasticSearchStatsService implements StatsService {

	public static final String INDEX = "arena-stats";

	private Client client;

	public ElasticSearchStatsService(Client client) {
		this.client = client;
	}

	public List<Stat> searchStats(String query) {
		return searchStats(query, 0);
	}

	public List<Stat> searchStats(String query, int from) {
		return getStatsForQuery(QueryBuilders.queryString(query), from);
	}

	public List<Stat> getLastStats() {
		return getLastStats(0);
	}

	public List<Stat> getLastStats(int from) {
		return getStatsForQuery(QueryBuilders.matchAllQuery(), from);
	}

	public List<Stat> getStatsForPlayer(Player player) {
		return getStatsForPlayer(player, 0);
	}

	public List<Stat> getStatsForPlayer(Player player, int from) {
		return getTypeSortedStatsForQuery(QueryBuilders.boolQuery()
				.should(QueryBuilders.termQuery("scores.player", player.getName()))
				.should(QueryBuilders.termQuery("name", player.getGame())), from);
	}

	public List<Stat> getStatsForGame(Game game) {
		return getStatsForGame(game, 0);
	}

	public List<Stat> getStatsForGame(Game game, int from) {
		return getStatsForQuery(QueryBuilders.matchQuery("game", game.getName()), from);
	}

	public List<Stat> getStatsForMatch(Match match) {
		return getStatsForMatch(match, 0);
	}

	public List<Stat> getStatsForMatch(Match match, int from) {
		return getTypeSortedStatsForQuery(QueryBuilders.boolQuery()
				.should(QueryBuilders.termQuery("name", match.getGame()))
				.should(QueryBuilders.termsQuery("name", match.getScores().stream().map(Score::getPlayer).collect(Collectors.toList()))), from);
	}

	private SearchRequestBuilder prepareQuery(QueryBuilder query) {
		return client.prepareSearch("arena-stats").setQuery(query);
	}

	private SearchRequestBuilder prepareRequest(SearchRequestBuilder searchRequestBuilder, int from) {
		searchRequestBuilder.addSort("_timestamp", SortOrder.DESC).setSize(6).setFrom(from);
		return searchRequestBuilder;
	}

	private List<Stat> getStatsForQuery(QueryBuilder query, int from) {
		return getStatsForRequest(prepareRequest(prepareQuery(query), from));
	}

	private List<Stat> getTypeSortedStatsForQuery(QueryBuilder query, int from) {
		return getStatsForRequest(prepareRequest(prepareQuery(query).addSort("_type", SortOrder.ASC), from));
	}

	private List<Stat> getStatsForRequest(SearchRequestBuilder request) {
		return Collections.emptyList();
	}
}
