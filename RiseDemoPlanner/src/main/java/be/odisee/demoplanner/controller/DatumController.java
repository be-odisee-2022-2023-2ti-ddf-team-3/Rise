package be.odisee.demoplanner.controller;

import be.odisee.demoplanner.domain.Datum;
import be.odisee.demoplanner.domain.Demo;
import be.odisee.demoplanner.service.DemoPlannerSessieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/")
public class DatumController {

    @Autowired
    protected DemoPlannerSessieService demoPlannerSessieService =null; // ready for dependency injection

    @GetMapping("/datum.html")
    public String index(ModelMap model){
        List<Datum> deLijst = demoPlannerSessieService.geefAlleDatums();
        model.addAttribute("datums", deLijst);
        return "/datum.html";
    }
    // je zal naar index.html gaan

    @RequestMapping(value={"/detailDatum.html"},method= RequestMethod.GET)
    public String datumDetail(@RequestParam("id") Integer id, ModelMap model){
        // Optional<Persoon> is nu nodig in deze versie van Spring  boot bij een opzoeking op id
        Datum datum = demoPlannerSessieService.zoekDatumMetId(id);
        model.addAttribute("datum",datum);
        return "/detailDatum";
    }
    // je zal naar persoon.html gaan

    @RequestMapping(value={"/nieuweDatum/{id}"},method=RequestMethod.GET)
    public String nieuwDatumFormulier(ModelMap model){
        Datum datum = new Datum();
        model.addAttribute("dedatum", datum);
        return "/nieuweDatum";
    }
    // je zal naar nieuwePersoon.html gaan

    @RequestMapping(value={"/nieuweDatum/{id}"},method=RequestMethod.POST)
    public String datumToevoegen(@ModelAttribute("dedatum") Datum datum, @PathVariable Integer id, ModelMap model){
        Datum toegevoegdeDatum = demoPlannerSessieService.voegDatumToe(
                datum.getStart(),
                datum.getEinde(),
                "onbevestigd",
                demoPlannerSessieService.zoekDemoMetId(id));
        return "redirect:detailDatum.html?id="+toegevoegdeDatum.getId();
    }
    // je zal naar de detailpagina van de toegevoegde persoon gaan

    @GetMapping(value={"/editDatum/{id}"})
    public String editDatumFormulier(@PathVariable Integer id, Model model) {
        model.addAttribute("demo", demoPlannerSessieService.zoekDemoMetId(id));
        return "editDatum";
    }

    @PostMapping("/updateDatum/{id}")
    public String datumUpdaten(@PathVariable Integer id, @ModelAttribute("datum") Datum datum, Model model) {
        // haal persoon op uit database
        Datum bestaandeDatum = demoPlannerSessieService.zoekDatumMetId(id);
        bestaandeDatum.setId(id);
        bestaandeDatum.setStart(datum.getStart());
        bestaandeDatum.setEinde(datum.getEinde());
        bestaandeDatum.setStatus(datum.getStatus());


        // bewaar persoon object
        demoPlannerSessieService.updateDatum(bestaandeDatum);
        return "redirect:/demo.html";
    }


    @GetMapping(value={"/deleteDatum/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String datumVerwijderen(@PathVariable(name = "id") Integer id) {
        demoPlannerSessieService.verwijderDatumMetId(id);
        return "redirect:/demo.html";
    }
}
