package com.farmSystem.farmSystem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.farmSystem.farmSystem.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
    User findByEmail(String email);
    User findById(int id);
    User findUserByUsernameAndPass(String username, String password);
    List<User> findAll();
    
}



