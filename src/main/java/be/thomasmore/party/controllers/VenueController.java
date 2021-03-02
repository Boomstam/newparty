package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class VenueController {
    @Autowired
    private VenueRepository venueRepository;

    private final static String show ="show", hide = "hide";
    private String currentFilter = show;

    @GetMapping({"/venuelist", "venuelist/{filter}"})
    public String venueList(Model model, @PathVariable(required = false) String filter) {
        Iterable<Venue> venues = venueRepository.findAll();
        model.addAttribute("venues", venues);
        if(filter==null)return "venuelist";
        flipFilter();
        model.addAttribute("filter", currentFilter);
        return "venuelist";
    }

    private void flipFilter(){
        if(currentFilter.equals(show)){
            currentFilter = hide;
        } else {
            currentFilter = show;
        }
    }
}