package be.odisee.demoplanner.controller;

import be.odisee.demoplanner.domain.Demo;
import be.odisee.demoplanner.domain.Persoon;
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
public class DemoController {

    @Autowired
    protected DemoPlannerSessieService demoPlannerSessieService =null; // ready for dependency injection

    @GetMapping("/demo.html")
    public String index(ModelMap model){
        List<Demo> deLijst = demoPlannerSessieService.geefAlleDemos();
        model.addAttribute("demos", deLijst);
        return "/demo.html";
    }
    // je zal naar index.html gaan

    @RequestMapping(value={"/detailDemo.html"},method=RequestMethod.GET)
    public String demoDetail(@RequestParam("id") Integer id, ModelMap model){
        // Optional<Persoon> is nu nodig in deze versie van Spring  boot bij een opzoeking op id
        Demo demo = demoPlannerSessieService.zoekDemoMetId(id);
        model.addAttribute("demo",demo);
        return "/detailDemo";
    }
    // je zal naar persoon.html gaan

    @RequestMapping(value={"/nieuweDemo.html"},method=RequestMethod.GET)
    public String nieuwDemoFormulier(ModelMap model){
        Demo demo = new Demo();
        model.addAttribute("dedemo", demo);
        return "/nieuweDemo";
    }
    // je zal naar nieuwePersoon.html gaan

    @RequestMapping(value={"/nieuweDemo.html"},method=RequestMethod.POST)
    public String demoToevoegen(@ModelAttribute("dedemo") Demo demo, ModelMap model){
        Demo toegevoegdeDemo = demoPlannerSessieService.voegDemoToe(
                demo.getNaam(),
                demo.getAdres(),
                "aangemaakt");
        System.out.println("DEBUG demogegevens naam: "+demo.getNaam());
        return "redirect:detailDemo.html?id="+toegevoegdeDemo.getId();
    }
    // je zal naar de detailpagina van de toegevoegde persoon gaan

    @GetMapping(value={"/editDemo/{id}"})
    public String editDemoFormulier(@PathVariable Integer id, Model model) {
        model.addAttribute("demo", demoPlannerSessieService.zoekDemoMetId(id));
        return "editDemo";
    }

    @PostMapping("/updateDemo/{id}")
    public String demoUpdaten(@PathVariable Integer id, @ModelAttribute("demo") Demo demo, Model model) {
        // haal persoon op uit database
        Demo bestaandeDemo = demoPlannerSessieService.zoekDemoMetId(id);
        bestaandeDemo.setId(id);
        bestaandeDemo.setNaam(demo.getNaam());
        bestaandeDemo.setAdres(demo.getAdres());
        bestaandeDemo.setStatus(demo.getStatus());

        // bewaar persoon object
        demoPlannerSessieService.updateDemo(bestaandeDemo);
        return "redirect:/demo.html";
    }


    @GetMapping(value={"/deleteDemo/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String demoVerwijderen(@PathVariable(name = "id") Integer id) {
        demoPlannerSessieService.verwijderDemoMetId(id);
        return "redirect:/demo.html";
    }
}
