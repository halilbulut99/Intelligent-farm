package com.farmSystem.farmSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AnimalController {

	@GetMapping("/")
	public String home() {
		return "home"; 
	}
	
	
	@GetMapping("/gallery")
	public String gallery() {
		return "gallery";
	}
	
	@GetMapping("/add")
	public String add() {
		return "add";
	}
	@GetMapping("/reg")
	public String registration() {
		return "reg";
	}
	
	@GetMapping("/about")
	public String about() {
		return "about";
	}
	
	@GetMapping("/List-animals")
	public String test1() {
		return "List-animals";
	}
	
	@GetMapping("/searchResult")
	public String searchResult() {
		return "searchResult";
	}
	
}
