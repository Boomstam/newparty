package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Artist;
import be.thomasmore.party.model.Party;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.ArtistRepository;
import be.thomasmore.party.repositories.PartyRepository;
import be.thomasmore.party.repositories.VenueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private PartyRepository partyRepository;
    @Autowired
    private VenueRepository venueRepository;
    @Autowired
    private ArtistRepository artistRepository;

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
        model.addAttribute("artists", artistRepository.findAll());
        model.addAttribute("venues", venueRepository.findAll());
        return "admin/partyedit";
    }

    @PostMapping("/partyedit/{id}")
    public String partyEditPost(Model model, @PathVariable int id,
                                @Valid @ModelAttribute("party") Party party,
                                BindingResult bindingResult, @RequestParam Integer venueID,
                                @RequestParam(required = false) Integer[] artistIDs) {
        logger.info("partyEditPost " + id + " -- new name=" + party.getName() + " -- venueID" + venueID);
        if (bindingResult.hasErrors()) {
            model.addAttribute("artists", artistRepository.findAll());
            model.addAttribute("venues", venueRepository.findAll());
            return "admin/partyedit";
        }
        if (venueID!=party.getVenue().getId()) {
            party.setVenue(new Venue(venueID));
        }
        Collection<Artist> artists = artistIDs==null ? null : artistRepository.findByIdIn(artistIDs);
        party.setArtists(artists);
        partyRepository.save(party);
        Iterable<Venue> venues = venueRepository.findAll();
        model.addAttribute("venues", venues);
        return "redirect:/partydetails/"+id;
    }

    @GetMapping("/partynew")
    public String partyNew(Model model) {
        logger.info("partynew");
        model.addAttribute("venues", venueRepository.findAll());
        model.addAttribute("artists", artistRepository.findAll());
        return "admin/partynew";
    }

    @PostMapping("/partynew")
    public String partyNewPost(Model model, @Valid @ModelAttribute("party") Party party,
                               BindingResult bindingResult, @RequestParam Integer venueID,
                               @RequestParam(required = false) Integer[] artistIDs) {
        logger.info("partyNewPost -- new name=" + party.getName());
        model.addAttribute("artists", artistRepository.findAll());
        model.addAttribute("venues", venueRepository.findAll());
        if (bindingResult.hasErrors()) {
            return "admin/partynew";
        }
        party.setVenue(new Venue(venueID));
        Collection<Artist> artists = artistIDs==null ? null : artistRepository.findByIdIn(artistIDs);
        party.setArtists(artists);
        partyRepository.save(party);
        Integer id = party.getId();
        return "redirect:/partydetails/" + id;
    }
}