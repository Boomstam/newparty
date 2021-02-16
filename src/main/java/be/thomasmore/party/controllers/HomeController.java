package be.thomasmore.party.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    private final int mySpecialNumber = 729;
    private final String[] venueNames = { "De Club", "De Loods", "Zapoi", "Nekkerhal"};

    @GetMapping({"/", "home"})
    public String home(Model model) {
        model.addAttribute("mijnSpeciaalNummer", mySpecialNumber);
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("mijnSpeciaalNummer", mySpecialNumber);
        return "about";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @GetMapping("/venuelist")
    public String venueList(Model model) {
        model.addAttribute("venues", venueNames);
        return "venuelist";
    }

    @GetMapping({"/venuedetails", "/venuedetails/{venueIndex}"})
    public String venueDetails(Model model, @PathVariable(required = false) String venueIndex) {
        try {
            int venueIndexVal = Integer.parseInt(venueIndex);
            int numNames = venueNames.length;
            if(venueIndexVal >= 0 && venueIndexVal < numNames){
                addVenueDetailsAttributes(model, venueIndexVal, numNames);
            }
        }
        catch (NumberFormatException e)
        {
            // This error is handled in the html.
        }
        return "venuedetails";
    }

    private void addVenueDetailsAttributes(Model model, int venueIndex, int numNames) {
        model.addAttribute("venueindex", venueIndex);
        String venueName = venueNames[venueIndex];
        model.addAttribute("venuename", venueName);
        int prevIndex = venueIndex - 1;
        int nextIndex = venueIndex + 1;
        if(prevIndex == -1){
            prevIndex = numNames - 1;
        }
        if(nextIndex == numNames){
            nextIndex = 0;
        }
        model.addAttribute("previndex", prevIndex);
        model.addAttribute("nextindex", nextIndex);
    }
}