package be.odisee.demoplanner.controller;

import be.odisee.demoplanner.domain.Planning;
import be.odisee.demoplanner.service.DemoPlannerSessieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class PlanningController {
    @Autowired
    protected DemoPlannerSessieService demoPlannerSessieService =null; // ready for dependency injection
    @GetMapping("/planning.html")
    public String index(ModelMap model){
        List<Planning> deLijst = demoPlannerSessieService.geefAllePlanningen();
        model.addAttribute("planningen", deLijst);
        return "/planning.html";
    }
    // je zal naar planning.html gaan
    @RequestMapping(value={"/detailPlanning.html"},method=RequestMethod.GET)
    public String planningDetail(@RequestParam("id") Integer id, ModelMap model){
        Planning planning = demoPlannerSessieService.zoekPlanningMetId(id);
        model.addAttribute("planning",planning);
        return "/detailPlanning.html";
    }
    // je zal naar planning.html gaan
    @PostMapping("/planning/{id}")
    public String updatePlanning(@PathVariable Integer id, @ModelAttribute("planning") Planning planning, Model model) {
        // haal planning op uit database
        Planning randomPlanning = demoPlannerSessieService.zoekPlanningMetId(id);
        randomPlanning.setId(id);
        randomPlanning.setStatus(planning.getStatus());
        randomPlanning.setAmbassadeur_id(planning.getAmbassadeur_id());

        // bewaar planning object
        demoPlannerSessieService.updatePlanning(randomPlanning);
        return "redirect:/planning.html";
    }
    @RequestMapping(value={"/nieuwePlanning.html"},method=RequestMethod.GET)
    public String nieuwPlanningFormulier(ModelMap model){
        Planning planning = new Planning();
        model.addAttribute("deplanning", planning);
        return "/nieuwePlanning";
    }
    // je zal naar nieuwePlanning.html gaan
    @RequestMapping(value={"/nieuwePlanning.html"},method=RequestMethod.POST)
    public String planningToevoegen(@ModelAttribute("deplanning") Planning planning, ModelMap model){
        Planning toegevoegdPlanning = demoPlannerSessieService.voegPlanningToe(
                planning.getStatus(),
                planning.getDatum_id(),
                planning.getAmbassadeur_id());
        System.out.println("DEBUG planningsgegevens status: "+planning.getStatus());
        return "redirect:detailPlanning.html?id="+toegevoegdPlanning.getId();
    }
    // je zal naar de detailpagina van de toegevoegde planning gaan
}
