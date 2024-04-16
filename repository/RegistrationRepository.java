package com.farmSystem.farmSystem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.farmSystem.farmSystem.entity.Animal;
import com.farmSystem.farmSystem.entity.Registration;

@Repository
public interface RegistrationRepository extends CrudRepository<Registration, Integer> {
    Registration findByName(String name);
    Registration findByEmail(String email);
    Registration findById(int id);
    Registration findByPass(String pass);
    Registration findByRpass(String rpass);
    Registration findByBulstat(String bulstat);
    Registration findByRole(String role);
    Registration findByPhone(int phone);
    
    
    
    List<Registration> findAll();
    
    
    
    
}



