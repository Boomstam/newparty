package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Party;
import be.thomasmore.party.repositories.PartyRepository;
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

    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    @GetMapping("/partyedit/{id}")
    public String partyEdit(Model model, @PathVariable int id) {
        logger.info("partyedit"+id);
        Optional<Party> optionalParty = partyRepository.findById(id);
        if (optionalParty.isPresent()) {
            model.addAttribute("party", optionalParty.get());
        }
        return "admin/partyedit";
    }

    @PostMapping("/partyedit/{id}")
    public String partyEditPost(Model model, @PathVariable int id, @RequestParam String partyName) {
        logger.info("partyeditpost : " + id + " -- new name: " + partyName);
        Optional<Party> optionalParty = partyRepository.findById(id);
        if (optionalParty.isPresent()) {
            Party party = optionalParty.get();
            party.setName(partyName);
            partyRepository.save(party);
            model.addAttribute("party", party);
        }
        return "admin/partyedit";
    }
}