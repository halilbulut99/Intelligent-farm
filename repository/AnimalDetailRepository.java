package com.farmSystem.farmSystem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.farmSystem.farmSystem.entity.AnimalDetail;



public interface AnimalDetailRepository extends CrudRepository<AnimalDetail, Integer> {
	AnimalDetail findByName(String name);
	AnimalDetail findByA(int a);
	AnimalDetail findByD3(int d3);
	AnimalDetail findByE(int e);
	AnimalDetail findByC(int c);
	AnimalDetail findByCa(int ca);
	AnimalDetail findByB1(int b1);
	AnimalDetail findByTemperature(int temperature);

	
	List<AnimalDetail> findAll();
}
