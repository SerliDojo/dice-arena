package com.serli.dojo.dicearea.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BordelTrucController {

	@RequestMapping(value = "/hello", produces = "application/json")
	public String hello() {
		return "{\"hello\":\"worlddsf\"}";
	}

}
