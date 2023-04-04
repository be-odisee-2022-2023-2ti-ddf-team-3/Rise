package be.odisee.demoplanner.controller;

import be.odisee.demoplanner.domain.Persoon;
import be.odisee.demoplanner.service.DemoPlannerSessieService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class PersoonController {

    @Autowired
    protected DemoPlannerSessieService demoPlannerSessieService =null; // ready for dependency injection

    @RequestMapping(value={"/home.html","/index.html","lijst.html"},method=RequestMethod.GET)
    public String index(ModelMap model){
        List<Persoon> deLijst = demoPlannerSessieService.geefAllePersonen();
        model.addAttribute("personen", deLijst);
        return "/index";
    }
    // je zal naar index.html gaan

    @RequestMapping(value={"/persoon.html"},method=RequestMethod.GET)
    public String persoonDetail(@RequestParam("id") Integer id, ModelMap model){
    	// Optional<Persoon> is nu nodig in deze versie van Spring  boot bij een opzoeking op id
        Persoon persoon = demoPlannerSessieService.zoekPersoonMetId(id);
        model.addAttribute("persoon",persoon);
        return "/persoon";
    }
    // je zal naar persoon.html gaan

    @RequestMapping(value={"/persoonviamail.html"},method=RequestMethod.GET)
    public String persoonDetailViaEmail(@RequestParam("email") String email, ModelMap model){
        Persoon persoon = demoPlannerSessieService.zoekPersoonMetEmail(email);
        model.addAttribute("persoon", persoon);
        return "/persoon";
    }
    // je zal naar persoon.jsp gaan

    @RequestMapping(value={"/nieuwePersoon.html"},method=RequestMethod.GET)
    public String persoonFormulier(ModelMap model){
        Persoon persoon = new Persoon();
        model.addAttribute("depersoon", persoon);
        return "/nieuwePersoon";
    }
    // je zal naar nieuwePersoon.html gaan

    @RequestMapping(value={"/nieuwePersoon.html"},method=RequestMethod.POST)
    public String persoonToevoegen(@ModelAttribute("depersoon") Persoon persoon, ModelMap model){
        Persoon toegevoegdPersoon = demoPlannerSessieService.voegPersoonToe(persoon.getVoornaam(),
                                                                            persoon.getFamilienaam(),
                                                                            persoon.getEmailadres(),
                                                                            persoon.getPaswoord());
        System.out.println("DEBUG persoonsgegevens familienaam: "+persoon.getFamilienaam());
        return "redirect:persoon.html?id="+toegevoegdPersoon.getId();
    }
    // je zal naar de detailpagina van de toegevoegde persoon gaan

//    @GetMapping(value={"/editPersoon/{id}"})
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public String editPersoon(@PathVariable(name = "id") Integer id) {
//        return "/index";
//    }

    @GetMapping(value={"/editPersoon/{id}"})
    public String editPersoon(@PathVariable Integer id, Model model) {
        model.addAttribute("persoon", demoPlannerSessieService.zoekPersoonMetId(id));
        return "editPersoon";

//        ModelAndView editView = new ModelAndView("editPersoon");
//        Persoon persoon = demoPlannerSessieService.zoekPersoonMetId(id);
////        model.addAttribute("persoon",persoon);
//        editView.addObject("persoon", persoon);
//        return editView;
    }
    @PostMapping("/personen/{id}")
    public String updatePersoon(@PathVariable Integer id, @ModelAttribute("persoon") Persoon persoon, Model model) {
        // haal persoon op uit database
        Persoon bestaandePersoon = demoPlannerSessieService.zoekPersoonMetId(id);
        bestaandePersoon.setId(id);
        bestaandePersoon.setVoornaam(persoon.getVoornaam());
        bestaandePersoon.setFamilienaam(persoon.getFamilienaam());
        bestaandePersoon.setEmailadres(persoon.getEmailadres());
        bestaandePersoon.setPaswoord(persoon.getPaswoord());

        // bewaar persoon object
        demoPlannerSessieService.updatePersoon(bestaandePersoon);
        return "redirect:/home.html";
    }
//    @PostMapping(value={"/edit/{id}"})
//    public String savePersoon(@PathVariable("id") Integer id, @RequestBody Persoon persoon) {
//        persoon.setId(id);
//        demoPlannerSessieService.updatePersoon(persoon);
//
//        return "redirect:home.html";
//    }

    @GetMapping(value={"/delete/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deletePersoon(@PathVariable(name = "id") Integer id) {
        demoPlannerSessieService.verwijderPersoonMetId(id);
        return "redirect:/home.html";
    }

}
