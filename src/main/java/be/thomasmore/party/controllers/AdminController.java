package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Party;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.PartyRepository;
import be.thomasmore.party.repositories.VenueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private PartyRepository partyRepository;
    @Autowired
    private VenueRepository venueRepository;

    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    @ModelAttribute("party")
    public Party findParty(@PathVariable(required = false) Integer id) {
        logger.info("findParty " + id);
        if (id!=null) {
            Optional<Party> optionalParty = partyRepository.findById(id);
            if (optionalParty.isPresent()) return optionalParty.get();
        }
        return new Party();
    }

    @GetMapping("/partyedit/{id}")
    public String partyEdit(Model model, @PathVariable int id) {
        logger.info("partyedit : " + id);
        Iterable<Venue> venues = venueRepository.findAll();
        model.addAttribute("venues", venues);
        return "admin/partyedit";
    }

    @PostMapping("/partyedit/{id}")
    public String partyEditPost(Model model, @PathVariable int id,
                                @ModelAttribute("party") Party party,
                                @RequestParam Integer venueID) {
        logger.info("partyEditPost " + id + " -- new name=" + party.getName()
           + " -- venueID" + venueID);
        if (venueID!=party.getVenue().getId()) {
            party.setVenue(new Venue(venueID));
        }
        partyRepository.save(party);
        Iterable<Venue> venues = venueRepository.findAll();
        model.addAttribute("venues", venues);
        return "redirect:/partydetails/"+id;
    }

    @GetMapping("/partynew")
    public String partyNew(Model model) {
        logger.info("partynew");
        model.addAttribute("venues", venueRepository.findAll());
        return "admin/partynew";
    }

    @PostMapping("/partynew")
    public String partyNewPost(Model model, @ModelAttribute("party") Party party,
                               @RequestParam Integer venueID) {
        logger.info("partyNewPost -- new name=" + party.getName() + ", date=" + party.getDate());
        party.setVenue(new Venue(venueID));
        partyRepository.save(party);
        Integer id = party.getId();
        return "redirect:/partydetails/" + id;
    }
}