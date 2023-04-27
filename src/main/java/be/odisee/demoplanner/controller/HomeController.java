package be.odisee.demoplanner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	// je zal naar login.html gaan

//	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
//	public String index() {
//		return "index";
//	}
	// je zal naar login.html gaan
	@RequestMapping(value={"/home.html"},method=RequestMethod.GET)
	public String home(ModelMap model){
		return "home";
	}

//	 je zal naar home.html gaan
}
