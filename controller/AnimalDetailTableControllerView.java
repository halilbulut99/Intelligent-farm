package com.farmSystem.farmSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.farmSystem.farmSystem.entity.Animal;
import com.farmSystem.farmSystem.entity.AnimalDetail;
import com.farmSystem.farmSystem.repository.AnimalDetailRepository;
import com.farmSystem.farmSystem.repository.AnimalRepository;

@Controller
public class AnimalDetailTableControllerView {

	
	@Autowired
    private AnimalDetailRepository animalDetailRepository;

    @GetMapping("/details")
    public String listAnimalDetails(Model model) {
        List<AnimalDetail> details = animalDetailRepository.findAll();
        model.addAttribute("details", details);
        return "List-animals";
    }

	
	
	
}
