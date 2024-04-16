package com.farmSystem.farmSystem.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.farmSystem.farmSystem.entity.AnimalDetail;
import com.farmSystem.farmSystem.service.AnimalDetailService;

@RestController
public class AnimalDetailController {
	
	private AnimalDetailService animalDetailService;
	
	@Autowired
	public AnimalDetailController(AnimalDetailService animalDetailService) {
		this. animalDetailService =  animalDetailService;
	}	
	
	@PostMapping(path = "/addDetail")
	public AnimalDetail detail( 
								@RequestParam(value = "name") String name,
								@RequestParam(value = "a") int a,
								@RequestParam(value = "d3") int d3,
								@RequestParam(value = "e") int e,
								@RequestParam(value = "c") int c,
								@RequestParam(value = "ca") int ca,
								@RequestParam(value = "b1") int b1,
								@RequestParam(value = "temperature") int temperature,
								@RequestParam(value = "date") String dateStr
								) {
		
	
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate date = LocalDate.parse(dateStr, formatter);
		Date datee = new Date(1,2,3);
	    return animalDetailService.registerUser(name,a,d3,e,c,ca,b1,temperature,date);	
	}
	
	@GetMapping(path = "/allDetails")
	public List<AnimalDetail> getAllDetails(){
		
		return animalDetailService.getAll();	
	}  
}
