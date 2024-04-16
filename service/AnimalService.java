package com.farmSystem.farmSystem.service;

import java.io.Console;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.crypto.Data;

//import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
/*import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;*/
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.farmSystem.farmSystem.entity.Animal;
import com.farmSystem.farmSystem.repository.AnimalRepository;

@Service
public class AnimalService {

	private AnimalRepository animalRepository;
	
	@Autowired
	public AnimalService(AnimalRepository userRepository) {
		this.animalRepository = userRepository;
	}

	public Animal registerUser(String name,String type,String breed,String father,String mother,int age,int weight,LocalDate birthDate) {
		
		
		Animal animal = new Animal(name,type,breed,father,mother,age,weight,birthDate);

		return animalRepository.save(animal);

	}
	
	public  Animal testMethoda(String name) {
		
		return null;
	}

	public List<Animal> getAll(){
		return animalRepository.findAll();
	}	
}
