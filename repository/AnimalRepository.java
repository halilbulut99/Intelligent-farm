package com.farmSystem.farmSystem.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.farmSystem.farmSystem.entity.Animal;
import com.farmSystem.farmSystem.entity.Registration;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Integer> {
    Animal findByName(String name);
    Animal findByType(String type);
    Animal findByBreed(String breed);
    Animal findByFather(String father);
    Animal findByMother(String mother);
    Animal findByAge(int age);
    Animal findByWeight(int weight);
    Animal findById(int id);

    List<Animal> findAll();
   }



