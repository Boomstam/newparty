package be.thomasmore.party.controllers;

import be.thomasmore.party.helpers.ShowHideToggler;
import be.thomasmore.party.model.Party;
import be.thomasmore.party.repositories.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Optional;

@Controller
public class PartyController {
    @Autowired
    private PartyRepository partyRepository;

    @GetMapping({"/partylist", "partylist/{filter}"})
    public String partyList(Model model, @PathVariable(required = false) String filter,
                            @RequestParam(required = false) String keyword) {
        filter = ShowHideToggler.oppositeFilter(filter);
        model.addAttribute("filter", filter);
        Iterable<Party> parties = partyRepository.findAll();
        if (keyword != null) {
            parties = partyRepository.findByKeyword(keyword);
        }
        Collection partyColl = (Collection)parties;
        model.addAttribute("numParties", partyColl.size());
        model.addAttribute("keyword", keyword);
        model.addAttribute("parties", partyColl);
        return "partylist";
    }

    @GetMapping({"/partydetails", "/partydetails/{idString}"})
    public String partyDetails(Model model, @PathVariable(required = false) String idString) {
        int id = -1;
        try {
            id = Integer.parseInt(idString);
        }
        catch (NumberFormatException e)
        {
        }
        Optional<Party> partyWrapper = partyRepository.findById(id);
        boolean hasArtists = false;
        if(partyWrapper.isPresent()){
            Party party = partyWrapper.get();
            model.addAttribute("party", party);
            hasArtists = party.getArtists().isEmpty() == false;
        } else {
            model.addAttribute("party", null);
        }
        model.addAttribute("hasArtists", hasArtists);
        int numParties = (int) partyRepository.count();
        model.addAttribute("previd", (id>=1 && id <= numParties) ? (id>1 ? id-1 : numParties) : null);
        model.addAttribute("nextid", (id>=1 && id <= numParties) ? (id<numParties ? id+1 : 1) : null);
        return "partydetails";
    }
}
