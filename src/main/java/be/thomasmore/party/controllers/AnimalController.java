package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Animal;
import be.thomasmore.party.model.Party;
import be.thomasmore.party.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    @GetMapping({"/animaldetails", "/animaldetails/{idString}"})
    public String animalDetails(Model model, @PathVariable(required = false) String idString) {
        int id = -1;
        try {
            id = Integer.parseInt(idString);
        }
        catch (NumberFormatException e)
        {
        }
        Optional<Animal> animalWrapper = animalRepository.findById(id);
        boolean hasParties = false;
        if(animalWrapper.isPresent()){
            Animal animal = animalWrapper.get();
            model.addAttribute("animal", animal);
            hasParties = animal.getParties().isEmpty() == false;
        } else {
            model.addAttribute("animal", null);
        }
        model.addAttribute("hasParties", hasParties);
        int numAnimals = (int) animalRepository.count();
        model.addAttribute("previd", (id>=1 && id <= numAnimals) ? (id>1 ? id-1 : numAnimals) : null);
        model.addAttribute("nextid", (id>=1 && id <= numAnimals) ? (id<numAnimals ? id+1 : 1) : null);
        return "animaldetails";
    }
}
