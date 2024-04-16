package com.farmSystem.farmSystem.service;

import java.io.Console;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.farmSystem.farmSystem.entity.Registration;
import com.farmSystem.farmSystem.repository.RegistrationRepository;

@Service
public class RegistrationService {

	private RegistrationRepository registrationRepository;
	
	@Autowired
	public RegistrationService(RegistrationRepository userRepository) {
		this.registrationRepository = userRepository;
	}
	
	public Registration registerUser(String name, String pass, String rpass, String email,String bulstat,String role,int phone,LocalDate birthDate) {
		
		Registration registration = new Registration(name,pass,rpass, email,bulstat,role,phone,birthDate);

		return registrationRepository.save(registration);

	}
	public  Registration testMethoda(String name) {
		
		return null;
	}
	public List<Registration> getAll(){
		 	return registrationRepository.findAll();
	}	
	
}
