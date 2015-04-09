package com.serli.dojo.dicearea.mvc;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.serli.dojo.dicearena.stats.service.ElasticSearchStatsService;

@SpringBootApplication
public class DiceArenaMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiceArenaMvcApplication.class, args);
    }
    
    @Bean
    public ElasticSearchStatsService getStatService(){
    	return new ElasticSearchStatsService(new TransportClient().addTransportAddress(new InetSocketTransportAddress("localhost", 9300)));
    }
}
