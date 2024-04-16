package com.farmSystem.farmSystem.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmSystem.farmSystem.entity.AnimalDetail;
import com.farmSystem.farmSystem.repository.AnimalDetailRepository;

@Service
public class AnimalDetailService {
	
private AnimalDetailRepository animalDetailRepository;
	
	@Autowired
	public AnimalDetailService(AnimalDetailRepository userRepository) {
		this.animalDetailRepository = userRepository;
	}
	
	public AnimalDetail registerUser(String name,int a, int d3, int e, int c, int ca, int b1, int temperature,LocalDate date) {
		
		
		AnimalDetail detail = new AnimalDetail(name,a, d3, e, c, ca, b1,temperature, date);

		return animalDetailRepository.save(detail);

	}
	
	public  AnimalDetail testMethoda(String name) {
		
		return null;
	}
	public List<AnimalDetail> getAll(){
		 	return animalDetailRepository.findAll();
	}	

}
