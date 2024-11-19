package com.markspetition.markspetition.controller;

import com.markspetition.markspetition.service.PetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/petitions")
public class PetitionController {

    @Autowired
    private PetitionService petitionService;


    @GetMapping("/home")
    public String homePage() {
        return "view-petitions";
    }

    @GetMapping("/all")
    public String viewAllPetitions(Model model) {
        model.addAttribute("petitions", petitionService.getAllPetitions());
        return "all-petitions"; // This is the page for all petitions
    }


}
