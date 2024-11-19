package com.markspetition.markspetition.service;

import com.markspetition.markspetition.model.Petition;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PetitionService {
    private List<Petition> petitions;

    public PetitionService() {
        this.petitions = new ArrayList<>();
        // Add some sample petitions
        petitions.add(new Petition(1, "Ban Plastic Cutlery", "Petition to ban plastic cutlery from local vendors."));
        petitions.add(new Petition(2, "Fund Irish Language Classes for Adults", "Petition to make Irish language learning more accessible."));
        petitions.add(new Petition(3, "Build New Cycle Paths", "Petition to have new cycle paths built in the city."));
        petitions.add(new Petition(4, "Free School Lunches for Children", "Petition to subsidse lunches for children in the area."));
    }

    public List<Petition> getAllPetitions() {
        return petitions;
    }

    public Petition getPetitionById(int id) {
        return petitions.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public void addPetition(Petition petition) {
        petitions.add(petition);
    }

    public List<Petition> getLatestThreePetitions() {
        List<Petition> latest = new ArrayList<>(petitions);
        Collections.reverse(latest); // Reverse the list to get most recent first
        return latest.stream().limit(3).toList(); // Return the first 3 items
    }


}
