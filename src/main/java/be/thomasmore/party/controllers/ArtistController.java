package be.thomasmore.party.controllers;

import be.thomasmore.party.helpers.ShowHideToggler;
import be.thomasmore.party.model.Artist;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Optional;

@Controller
public class ArtistController {
    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping({"/artistlist", "artistlist/{filter}"})
    public String venueList(Model model, @PathVariable(required = false) String filter,
                            @RequestParam(required = false) String keyword) {
        filter = ShowHideToggler.oppositeFilter(filter);
        model.addAttribute("filter", filter);
        Iterable<Artist> artists = artistRepository.findAll();
        Collection artistColl = (Collection)artists;
        if (keyword != null) {
            artists = artistRepository.findByKeyword(keyword);
        }
        artistColl = (Collection)artists;
        model.addAttribute("numArtists", artistColl.size());
        model.addAttribute("keyword", keyword);
        model.addAttribute("artists", artistColl);
        return "artistlist";
    }

    @GetMapping({"/artistdetails", "/artistdetails/{idString}"})
    public String artistDetails(Model model, @PathVariable(required = false) String idString) {
        int id = -1;
        try {
            id = Integer.parseInt(idString);
        }
        catch (NumberFormatException e)
        {
        }
        Optional<Artist> artist = artistRepository.findById(id);
        if(artist.isPresent()){
            model.addAttribute("artist", artist.get());
        } else {
            model.addAttribute("artist", null);
        }
        int numArtists = (int)artistRepository.count();
        model.addAttribute("previd", (id>=1 && id <= numArtists) ? (id>1 ? id-1 : numArtists) : null);
        model.addAttribute("nextid", (id>=1 && id <= numArtists) ? (id<numArtists ? id+1 : 1) : null);
        return "artistdetails";
    }
}