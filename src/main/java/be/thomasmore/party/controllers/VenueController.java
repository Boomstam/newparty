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

    private final Venue[] venues = {
            new Venue("De Club", "http://declubinfo"),
            new Venue("De Loods", "http://deloodsinfo"),
            new Venue("Zapoi", "http://zapoiinfo"),
            new Venue("Nekkerhal", "http://nekkerhalinfo")
    };
    private String outdoorFilter = "all", indoorFilter = "all", capacityFilter = "all";

    @GetMapping("/venuelist")
    public String venueList(Model model) {
        Iterable<Venue> venues = venueRepository.findAll();
        model.addAttribute("venues", venues);
        model.addAttribute("outdoorFilter", outdoorFilter);
        model.addAttribute("indoorFilter", indoorFilter);
        model.addAttribute("capacityFilter", capacityFilter);
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

    @GetMapping({"/venuelist/outdoor", "/venuelist/outdoor/{outdoorFilter}"})
    public String venueListOutdoorFilter(Model model, @PathVariable(required = false) String outdoorFilter) {
        Iterable<Venue> venues;
        if(outdoorFilter == null){
            outdoorFilter = "all";
        }
        if(outdoorFilter.equals("yes")){
            venues = venueRepository.findByOutdoor(true);
        } else if(outdoorFilter.equals("no")){
            venues = venueRepository.findByOutdoor(false);
        } else {
            venues = venueRepository.findAll();
            outdoorFilter = "all";
        }
        model.addAttribute("venues", venues);
        model.addAttribute("outdoorFilter", outdoorFilter);
        model.addAttribute("indoorFilter", indoorFilter);
        model.addAttribute("capacityFilter", capacityFilter);
        this.outdoorFilter = outdoorFilter;
        return "venuelist";
    }

    @GetMapping({"/venuelist/indoor", "/venuelist/indoor/{indoorFilter}"})
    public String venueListIndoorFilter(Model model, @PathVariable(required = false) String indoorFilter) {
        Iterable<Venue> venues;
        if(indoorFilter == null){
            indoorFilter = "all";
        }
        if(indoorFilter.equals("yes")){
            venues = venueRepository.findByIndoor(true);
        } else if(indoorFilter.equals("no")){
            venues = venueRepository.findByIndoor(false);
        } else {
            venues = venueRepository.findAll();
            indoorFilter = "all";
        }
        model.addAttribute("venues", venues);
        model.addAttribute("outdoorFilter", outdoorFilter);
        model.addAttribute("indoorFilter", indoorFilter);
        model.addAttribute("capacityFilter", capacityFilter);
        this.indoorFilter = indoorFilter;
        return "venuelist";
    }

    @GetMapping({"/venuelist/capacity", "/venuelist/capacity/{capacityFilter}"})
    public String venueListCapacityFilter(Model model, @PathVariable(required = false) String capacityFilter) {
        Iterable<Venue> venues;
        if(capacityFilter == null){
            capacityFilter = "all";
        }
        if(capacityFilter.equals("S")){
            venues = venueRepository.findByCapacityLessThanEqual(200);
        } else if(capacityFilter.equals("M")){
            venues = venueRepository.findByCapacityLessThanEqual(500);
        } else if(capacityFilter.equals("L")){
            venues = venueRepository.findByCapacityGreaterThan(500);
        } else {
            venues = venueRepository.findAll();
            capacityFilter = "all";
        }
        model.addAttribute("venues", venues);
        model.addAttribute("outdoorFilter", outdoorFilter);
        model.addAttribute("indoorFilter", indoorFilter);
        model.addAttribute("capacityFilter", capacityFilter);
        this.capacityFilter = capacityFilter;
        return "venuelist";
    }
}