package com.serli.dojo.dicearena.stats.service;

import java.util.Collections;
import java.util.List;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;

import com.serli.dojo.dicearena.stats.model.Game;
import com.serli.dojo.dicearena.stats.model.Match;
import com.serli.dojo.dicearena.stats.model.Player;
import com.serli.dojo.dicearena.stats.model.Stat;

public class ElasticSearchStatsService implements StatsService {

	public static final String INDEX = "arena-stats";

	private String elasticsearchHost;
	private int elasticsearchPort;

	public ElasticSearchStatsService(String elasticsearchHost, int elasticsearchPort) {
		this.elasticsearchHost = elasticsearchHost;
		this.elasticsearchPort = elasticsearchPort;
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
				.should(QueryBuilders.termQuery("scores.player", player.name))
				.should(QueryBuilders.termQuery("name", player.game)), from);
	}

	public List<Stat> getStatsForGame(Game game) {
		return getStatsForGame(game, 0);
	}

	public List<Stat> getStatsForGame(Game game, int from) {
		return getStatsForQuery(QueryBuilders.matchQuery("game", game.name), from);
	}

	public List<Stat> getStatsForMatch(Match match) {
		return getStatsForMatch(match, 0);
	}

	public List<Stat> getStatsForMatch(Match match, int from) {
		return getTypeSortedStatsForQuery(QueryBuilders.boolQuery()
				.should(QueryBuilders.termQuery("name", match.game))
				.should(QueryBuilders.termsQuery("name", match.scores.keySet())), from);
	}

	private SearchRequestBuilder prepareQuery(QueryBuilder query) {
		Client client = getClient();
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

	private List<Stat> getStatsForRequest(SearchRequestBuilder prepareRequest) {
		return Collections.emptyList();
	}

	protected Client getClient() {
		return new TransportClient().addTransportAddress(new InetSocketTransportAddress(elasticsearchHost, elasticsearchPort));
	}
}
