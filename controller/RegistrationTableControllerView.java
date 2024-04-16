package com.farmSystem.farmSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.farmSystem.farmSystem.entity.Registration;
import com.farmSystem.farmSystem.repository.RegistrationRepository;



@Controller
public class RegistrationTableControllerView {
	@Autowired
    private RegistrationRepository registrationRepository;

    @GetMapping("/registration")
    public String listRegistrations(Model model) {
        List<Registration> registrations = registrationRepository.findAll();
        model.addAttribute("registration", registrations);
        return "reg";
    }
	
}
