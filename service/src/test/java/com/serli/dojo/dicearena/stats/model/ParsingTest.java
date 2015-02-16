package com.serli.dojo.dicearena.stats.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ParsingTest {

	@Test
	public void testParsingAccount() throws Exception {
		Account value = new ObjectMapper().findAndRegisterModules()
				.readValue("{\"email\":\"aubergine@caramail.mock\", \"locations\":\"[ 5.40858, 43.29171 ]\", \"subscription\":\"2014-06-12\"}", Account.class);
		Assert.assertNotNull(value);
		Assert.assertEquals("aubergine@caramail.mock", value.getEmail());
	}

	@Test
	public void testParsingMatch() throws Exception {
		Match value = new ObjectMapper()
				.findAndRegisterModules()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.readValue(
						"{ \"id\": \"79\", \"game\": \"10000\", \"startTime\": \"2014-03-27T05:01\", \"endTime\": \"2014-03-27T05:05\", \"scores\": [ { \"player\": \"Mini Salsifis\", \"score\": 29 }, { \"player\": \"Chief Topinambour\", \"score\": 23 }, { \"player\": \"Incredible Salade\", \"score\": 88 } ], \"winner\": \"Incredible Salade\" }",
						Match.class);
		Assert.assertNotNull(value);
		Assert.assertEquals("10000", value.getGame());
	}

	@Test
	public void testPrintingMatch() throws Exception {
		Match value = new Match(23L, "Dudo", LocalDateTime.parse("2014-03-27T05:01"), LocalDateTime.parse("2014-03-27T05:05"));	
		List<Score> scores = new ArrayList<>();
		scores.add(new Score("Mini Salsifis", 29));
		scores.add(new Score("Chief Topinambour", 23));
		scores.add(new Score("Incredible Salade", 88));
		value.setScores(scores);

		System.out.println(new ObjectMapper().findAndRegisterModules().writeValueAsString(value));
	}

	@Test
	public void testParsingPlayer() throws Exception {
		Player value = new ObjectMapper().findAndRegisterModules()
				.readValue("{\"name\": \"Major Ciboulette\", \"game\": \"Dudo\", \"account\": \"ciboulette@gmail.coco\"}", Player.class);
		Assert.assertNotNull(value);
		Assert.assertEquals("Major Ciboulette", value.getName());
	}

	@Test
	public void testParsingGame() throws Exception {
		Game value = new ObjectMapper().findAndRegisterModules()
				.readValue("{\"name\": \"Dudo\", \"minPlayers\": 2, \"description\": \"Each player starts having five dice and a cup.\"}", Game.class);
		Assert.assertNotNull(value);
		Assert.assertEquals("Dudo", value.getName());
	}
}
