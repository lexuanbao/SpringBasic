package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@RequestMapping(value = "/index")
	   public String index() {
	      return "index";
	   }
	
	@RequestMapping("/welcome1")
	public String showWelcome1() {
		return "welcome1";
	}
}
