package be.odisee.demoplanner.controller;

import be.odisee.demoplanner.domain.Demo;
import be.odisee.demoplanner.service.DemoPlannerSessieService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")

public class DemoController {

    @Autowired
    protected DemoPlannerSessieService demoPlannerSessieService = null; // ready for dependecy injection

    @RequestMapping(value = "/demo.html", method = RequestMethod.GET)
    public String index(ModelMap model){
        List<Demo> deLijst = demoPlannerSessieService.geefAlleDemos();
        model.addAttribute("demos", deLijst);
        return "/demo";
    }
    // je zal naar demo.html gaan

    @RequestMapping(value = "/demo.html", method = RequestMethod.GET)
    public String demoDetail(@RequestParam("id") Integer id, ModelMap model) {
        // Optional <demos> is nu nodig in deze versie van Spring boot bij een opzoeking op id
        Demo demo = demoPlannerSessieService.zoekDemoMetId(id);
        model.addAttribute("demo", demo);
        return "/demo";
    }

    // je zal naar demo.html gaan

    @RequestMapping(value = "demo.html", method = RequestMethod.GET)
    public String demoDetailViaNaam(@RequestParam("naam") String naam, ModelMap model){
        Demo demo = demoPlannerSessieService.zoekDemoMetNaam(naam);
        model.addAttribute("demo", demo);
        return "/demo";
    }

    @RequestMapping(value = "demo.html", method = RequestMethod.GET)
    public String demoToevoegen(@ModelAttribute("dedemo") Demo demo, ModelMap model){
        Demo toegevoegdeDemo = demoPlannerSessieService.voegDemoToe(demo.getNaam(), demo.getAdres(), demo.getStatus());
        return "redirect:demo.html?id="+toegevoegdeDemo.getId();
    }

    @GetMapping(value = "/editDemo/{id}")
    public String editDemo(@PathVariable(name = "id") Integer id, ModelMap model){
        Demo demo = demoPlannerSessieService.zoekDemoMetId(id);
        model.addAttribute("demo", demo);
        return "editDemo";
    }

    // todo: /editDemo/{id} maken

    @GetMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deletePersoon(@PathVariable(name = "id") Integer id) {
        demoPlannerSessieService.verwijderDemoMetId(id);
        return "/index";
    }

    @GetMapping(value={"/demo"})
    public String getdemo() {
        return "demo";
    }

}
