package be.thomasmore.party.controllers;

import be.thomasmore.party.helpers.ShowHideToggler;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
public class VenueController {
    @Autowired
    private VenueRepository venueRepository;

    @GetMapping({"/venuelist", "venuelist/{filter}"})
    public String venueList(Model model, @PathVariable(required = false) String filter,
                                         @RequestParam(required = false) Integer minimumCapacity,
                                         @RequestParam(required = false) Integer maximumCapacity,
                                         @RequestParam(required = false) Double maximumDistance,
                                         @RequestParam(required = false) String foodProvided) {
        filter = ShowHideToggler.oppositeFilter(filter);
        model.addAttribute("filter", filter);
        System.out.print(foodProvided);
        Iterable<Venue> venues = venueRepository.findAll();
        Collection venueColl = (Collection)venues;
        if(minimumCapacity == null && maximumCapacity == null && maximumDistance == null && foodProvided == null){
        } else {
            Boolean isFoodProvided = null;
            if(foodProvided != null){
                if(foodProvided.equals("yes")){
                    isFoodProvided = true;
                } else if(foodProvided.equals("no")){
                    isFoodProvided = false;
                }
            }
            /*int minCap = 0, maxCap = 0, maxDist = 0;
            if(minimumCapacity != null) {
                minCap = minimumCapacity.intValue();
            }
            if(maximumCapacity == null){
                maxCap = maximumCapacity.intValue();
            }
            if(maximumDistance == null){
                maxDist = maximumDistance.intValue();
            }*/
            venues = venueRepository.findByCompositeFilters(
                    minimumCapacity, maximumCapacity, maximumDistance, isFoodProvided, null, null);
            //venues = venueRepository.findByFoodProvided(isFoodProvided, minimumCapacity, maximumCapacity);//, maximumDistance);
            //venues = venueRepository.findByMaxDistance(maximumDistance);
            venueColl = (Collection)venues;
        }
        model.addAttribute("numVenues", venueColl.size());
        model.addAttribute("venues", venueColl);
        return "venuelist";
    }

    @GetMapping({"/venuedetails", "/venuedetails/{idString}"})
    public String venueDetails(Model model, @PathVariable(required = false) String idString) {
        int id = -1;
        try {
            id = Integer.parseInt(idString);
        }
        catch (NumberFormatException e)
        {
        }
        Optional<Venue> venue = venueRepository.findById(id);
        if(venue.isPresent()){
            model.addAttribute("venue", venue.get());
        } else {
            model.addAttribute("venue", null);
        }
        int numVenues = (int)venueRepository.count();
        model.addAttribute("previd", (id>=1 && id <= numVenues) ? (id>1 ? id-1 : numVenues) : null);
        model.addAttribute("nextid", (id>=1 && id <= numVenues) ? (id<numVenues ? id+1 : 1) : null);
        return "venuedetails";
    }
}