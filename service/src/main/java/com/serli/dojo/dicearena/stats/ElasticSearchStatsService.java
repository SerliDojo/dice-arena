package com.serli.dojo.dicearena.stats;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

public class ElasticSearchStatsService implements StatsService {

	private String elasticsearchHost;
	private int elasticsearchPort;

	public ElasticSearchStatsService(String elasticsearchHost, int elasticsearchPort) {
		this.elasticsearchHost = elasticsearchHost;
		this.elasticsearchPort = elasticsearchPort;
	}

	public void searchStats(String query) {
		try(Client client = getClient()) {
			
		}
	}

	public void searchStats(String query, int from) {
	}

	public void getLastStats() {
	}

	public void getLastStats(int from) {
	}

	public void getStatsForPlayer(String name) {
	}

	public void getStatsForPlayer(String name, int from) {
	}

	public void getStatsForGame(String name) {
	}

	public void getStatsForGame(String name, int from) {
	}

	public void getStatsForMatch(String name) {
	}

	public void getStatsForMatch(String name, int from) {
	}

	protected Client getClient() {
		return new TransportClient().addTransportAddress(new InetSocketTransportAddress(elasticsearchHost, elasticsearchPort));
	}
}
