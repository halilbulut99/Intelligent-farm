package com.farmSystem.farmSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.farmSystem.farmSystem.entity.Animal;
import com.farmSystem.farmSystem.repository.AnimalRepository;

@Controller
public class AnimalTableControllerView {
	
	@Autowired
    private AnimalRepository animalRepository;

    @GetMapping("/animals")
    public String listAnimals(Model model) {
        List<Animal> animals = animalRepository.findAll();
        model.addAttribute("animals", animals);
        return "add";
    }

}
