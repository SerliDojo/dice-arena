package com.serli.dojo.dicearena.data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class dedicated to carry match informations like players and their scores.
 * 
 * @author Laurent
 */
public class Match implements Entity {

	public static final String TYPE = "match";
	public static final String MAPPING = "{ \"match\": { \"properties\": { \"id\": { \"type\": \"string\" }, \"game\": { \"type\": \"string\" }, \"startTime\": { \"type\": \"date\", \"format\": \"dateOptionalTime\" }, \"endTime\": { \"type\": \"date\", \"format\": \"dateOptionalTime\" }, \"winner\": { \"type\": \"string\" }, \"scores\": { \"properties\": { \"player\": { \"type\": \"string\" }, \"score\": { \"type\": \"long\" } } } } }}";

	public Long id;
	public Game game;
	public LocalDateTime startTime, endTime;
	public Map<String, Integer> scores = new HashMap<>();

	public Match(Long id, Game game, LocalDateTime startTime, LocalDateTime endTime) {
		this.id = id;
		this.game = game;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public String getId() {
		return id.toString();
	}

	@Override
	public String toJsonString() {
		Optional<Entry<String, Integer>> winner = scores.entrySet().stream().collect(Collectors.maxBy((o1, o2) -> {
			return o1.getValue().compareTo(o2.getValue());
		}));

		String winnerString = "";
		if (winner.isPresent()) {
			winnerString = winner.get().getKey();
		}

		String scoresString = scores.entrySet().stream().map(entry -> {
			return String.format("{ \"player\" : \"%s\", \"score\" : %d }", entry.getKey(), entry.getValue());
		}).collect(Collectors.joining(", "));

		return String.format("{ \"id\" : \"%d\", \"game\" : \"%s\", \"startTime\" : \"%s\", \"endTime\" : \"%s\", \"scores\" : [ %s ], \"winner\" : \"%s\" }",
				id, game.name, startTime, endTime, scoresString, winnerString);
	}
}