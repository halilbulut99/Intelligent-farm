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
/*import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;*/
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.farmSystem.farmSystem.entity.Animal;
import com.farmSystem.farmSystem.entity.Registration;
import com.farmSystem.farmSystem.service.RegistrationService;

import jakarta.servlet.http.HttpSession;

@RestController
public class RegistrationController {
	
	private RegistrationService registrationService;
	
	@Autowired
	public RegistrationController(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}	
	
	@PostMapping(path = "/registerr")
	public Registration register( @RequestParam(value = "email") String email, 
								@RequestParam(value = "name") String name, 
								@RequestParam(value = "pass") String pass, 
								@RequestParam(value = "rpass") String rpass,
								@RequestParam(value = "bulstat") String bulstat,
								@RequestParam(value = "role") String role,
								@RequestParam(value = "phone") int phone,
	                            @RequestParam(value = "birthDate") String birthDateStr
	                        ) {
	
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate birthDate = LocalDate.parse(birthDateStr, formatter);
		Date date = new Date(1,2,3);
		return registrationService.registerUser(name, pass, rpass, email,bulstat,role,phone,birthDate);	
		
	}
	@GetMapping(path = "/allReg")
	public List<Registration> getAllRegistrations(){
		
		return registrationService.getAll();	
	}
	
}
