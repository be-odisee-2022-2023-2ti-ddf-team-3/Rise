package be.odisee.demoplanner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "login";
	}
	// je zal naar login.html gaan
	@RequestMapping(value={"/home.html"},method=RequestMethod.GET)
	public String home(ModelMap model){
		return "/home";
	}

	// je zal naar home.html gaan
}
