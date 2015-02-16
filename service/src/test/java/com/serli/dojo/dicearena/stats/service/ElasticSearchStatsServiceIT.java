package com.serli.dojo.dicearena.stats.service;

import java.util.List;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.junit.Assert;
import org.junit.Test;

import com.serli.dojo.dicearena.stats.model.Stat;

public class ElasticSearchStatsServiceIT {

	@Test
	public void test() {
		try (TransportClient client = new TransportClient()) {
			ElasticSearchStatsService service = new ElasticSearchStatsService(
					client.addTransportAddress(new InetSocketTransportAddress("localhost", 9300)));

			List<Stat> stats = service.searchStats("Ail");
			Assert.assertEquals(6, stats.size());
			stats.stream().map(Stat::toJsonString).forEach(System.out::println);
		}
	}

}
