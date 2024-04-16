package com.farmSystem.farmSystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.farmSystem.farmSystem.agent.AnimalOntology;
import com.farmSystem.farmSystem.agent.FermerAgent;
import com.farmSystem.farmSystem.entity.Animal;

@Controller
public class AgentController {
	
	private AnimalOntology animalOntology;
	
	@GetMapping("/search")
    public String searchForm(Model model) {
        model.addAttribute("breedName", "");
        model.addAttribute("searchResult", new ArrayList<Animal>());
        return "searchResult";
    }
	
	

    @PostMapping("/search")
    public String search(@RequestParam("breedName") String breedName, Model model) {
        // Perform the breed search logic (similar to your ActionListener logic)
        List<Animal> searchResult = animalOntology.getAnimalByBreed(breedName); // Implement this method

        // Update the model with search results
        model.addAttribute("breedName", breedName);
        model.addAttribute("searchResult", searchResult);

        return "searchResult";
    }
    

    // Implement the performBreedSearch method to search for animals based on breed
}
