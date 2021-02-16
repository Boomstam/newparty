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
    public String error(){
        return "error";
    }

    /*@GetMapping({"/venuedetails", "/venuedetails/{venueName}"})
    public String venueDetails(Model model, @PathVariable(required = false) String venueName) {
        model.addAttribute("venuename", venueName);
        return "venuedetails";
    }

    @GetMapping({"/venuedetailsbyindex", "/venuedetailsbyindex/{venueIndex}"})
    public String venueDetailsByIndex(Model model, @PathVariable(required = false) String venueIndex) {
        try {
            int venueIndexVal = Integer.parseInt(venueIndex);

            if(venueIndexVal >= 0 && venueIndexVal < venueNames.length){
                String venueName = venueNames[venueIndexVal];
                model.addAttribute("venuename", venueName);
            }
        }
        catch (NumberFormatException e)
        {
            // This error is handled in the html.
        }
        return "venuedetails";
    }*/

    @GetMapping({"/venuedetails", "/venuedetails/{venueIndex}"})
    public String venueDetails(Model model, @PathVariable(required = false) String venueIndex) {
        try {
            int venueIndexVal = Integer.parseInt(venueIndex);

            if(venueIndexVal >= 0 && venueIndexVal < venueNames.length){
                String venueName = venueNames[venueIndexVal];
                model.addAttribute("venuename", venueName);
            }
        }
        catch (NumberFormatException e)
        {
            // This error is handled in the html.
        }
        return "venuedetails";
    }

    @GetMapping("/venuelist")
    public String venueList(Model model) {
        model.addAttribute("venues", venueNames);
        return "venuelist";
    }
}