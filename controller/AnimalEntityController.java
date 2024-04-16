package com.farmSystem.farmSystem.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
/*import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;*/
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.farmSystem.farmSystem.entity.Animal;
import com.farmSystem.farmSystem.service.AnimalService;

import jakarta.servlet.http.HttpSession;

@RestController
public class AnimalEntityController {
	
	private AnimalService animalService;
	
	@Autowired
	public AnimalEntityController(AnimalService animalService) {
		this.animalService = animalService;
	}	
	
	@PostMapping(path = "/addAnimal")
	public Animal register( 
								@RequestParam(value = "name") String name,
								@RequestParam(value = "type") String type,
								@RequestParam(value = "breed") String breed,
								@RequestParam(value = "father") String father,
								@RequestParam(value = "mother") String mother,
								@RequestParam(value = "age") int age,
								@RequestParam(value = "weight") int weight,
								@RequestParam(value = "birthDate") String birthDateStr
								) {
		
	
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate birthDate = LocalDate.parse(birthDateStr, formatter);
		Date date = new Date(1,2,3);
	    return animalService.registerUser(name,type,breed,father,mother,age,weight,birthDate);	
	}
	
	@GetMapping(path = "/all")
	public List<Animal> getAllAnimals(){
		
		return animalService.getAll();	
	}
}