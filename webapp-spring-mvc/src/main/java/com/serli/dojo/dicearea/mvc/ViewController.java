package com.serli.dojo.dicearea.mvc;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.serli.dojo.dicearena.stats.model.Game;
import com.serli.dojo.dicearena.stats.model.Match;
import com.serli.dojo.dicearena.stats.service.ElasticSearchStatsService;

@Controller
public class ViewController {

	@Autowired
	ElasticSearchStatsService statService;

	@RequestMapping("/helloView")
	public String index(Model model) {
		String message = "Hello World";
		model.addAttribute("message", message);
		return "hello";
	}

	@RequestMapping("/game/{name}")
	public String game(Model model, @PathVariable String name) {
		Game game = statService.getGame(name);
		model.addAttribute("game", game);
		model.addAttribute("stats", 
				statService.getStatsForGame(game).
				stream().
				filter(
						stat -> Match.class.isAssignableFrom(stat.getClass()))
						.collect(Collectors.toList()));
		return "game";
	}

}
