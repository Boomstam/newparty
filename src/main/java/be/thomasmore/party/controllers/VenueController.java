package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class VenueController {
    @Autowired
    private VenueRepository venueRepository;

    private final static String show ="show", hide = "hide";
    //private String currentFilter = hide;

    @GetMapping({"/venuelist", "venuelist/{filter}"})
    public String venueList(Model model, @PathVariable(required = false) String filter,
                                         @RequestParam(required = false) Integer minimumCapacity,
                                         @RequestParam(required = false) Integer maximumCapacity) {

        model.addAttribute("numVenues", venueRepository.count());
        filter = oppositeFilter(filter);
        model.addAttribute("filter", filter);
        Iterable<Venue> venues = venueRepository.findByCapacity(minimumCapacity, maximumCapacity);
        model.addAttribute("venues", venues);
        return "venuelist";
    }

    private String oppositeFilter(String filter){
        if(filter==null || filter.equals(hide)){
            return show;
        }
        if(filter.equals(show)){
            return hide;
        }
        return show;
    }
}