package com.markspetition.markspetition.controller;

import com.markspetition.markspetition.model.Petition;
import com.markspetition.markspetition.service.PetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/petitions")
public class PetitionController {

    @Autowired
    private PetitionService petitionService;


    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("latestPetitions", petitionService.getLatestThreePetitions());
        return "view-petitions";
    }

    @GetMapping("/all")
    public String viewAllPetitions(Model model) {
        model.addAttribute("petitions", petitionService.getAllPetitions());
        return "all-petitions"; // This is the page for all petitions
    }

    @GetMapping("/create")
    public String createPetitionPage() {
        return "create-petition";
    }

    @PostMapping("/create")
    public String createPetition(@RequestParam String title, @RequestParam String description) {
        int newId = petitionService.getAllPetitions().size() + 1;
        petitionService.addPetition(new Petition(newId, title, description));
        return "redirect:/petitions/home";
    }

    @GetMapping("/{id}")
    public String viewPetition(@PathVariable int id, Model model) {
        Petition petition = petitionService.getPetitionById(id);
        if (petition == null) {
            return "error";
        }
        model.addAttribute("petition", petition);
        return "view-petition";
    }

    @PostMapping("/{id}/sign")
    public String signPetition(@PathVariable int id, @RequestParam String name, @RequestParam String email) {
        Petition petition = petitionService.getPetitionById(id);
        if (petition != null) {
            petition.addSignature(name + " (" + email + ")");

        }
        return "redirect:/petitions/" + id;
    }

    @GetMapping("/search")
    public String searchPage() {
        return "search-petition";
    }


    @PostMapping("/search")
    public String searchPetition(@RequestParam String query, Model model) {
        List<Petition> results = petitionService.getAllPetitions().stream()
                .filter(p -> p.getTitle().toLowerCase().contains(query.toLowerCase()))
                .toList();
        model.addAttribute("results", results);
        return "search-results";
    }


}
