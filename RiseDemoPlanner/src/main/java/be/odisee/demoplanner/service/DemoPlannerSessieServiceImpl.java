package be.odisee.demoplanner.service;

import be.odisee.demoplanner.domain.*;
import be.odisee.demoplanner.dao.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service("DemoPlannerSessieService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
public class DemoPlannerSessieServiceImpl implements DemoPlannerSessieService {

    @Autowired
    private PersoonRepository persoonRepository = null;

    public DemoPlannerSessieServiceImpl(){}

    public List<Persoon> geefAllePersonen() {
        return persoonRepository.findAll();
    }

	@Transactional(propagation= Propagation.REQUIRED,readOnly=false)
    public Persoon zoekPersoonMetId(int id){
		Persoon persoon;
		
		Optional<Persoon> optionalPersoon = persoonRepository.findById(id);
        if ( optionalPersoon.isPresent() ) persoon = optionalPersoon.get();
        else persoon = null; // in dat geval hebben we geen persoon met dat id gevonden
		return persoon;
    }
    
	@Transactional(propagation= Propagation.REQUIRED,readOnly=false)
	@Override
	public Persoon zoekPersoonMetEmail(String email) {
		return persoonRepository.findByEmailadres(email);
	}

    @Transactional(propagation= Propagation.REQUIRED,readOnly=false)
    public Persoon voegPersoonToe(String voornaam, String familienaam, String emailadres, String paswoord) {

        return persoonRepository.save( createPersoon(voornaam,familienaam,emailadres,paswoord) );
    }

    private Persoon createPersoon( String voornaam, String familienaam, String emailadres, String paswoord) {

        return new Persoon( voornaam, familienaam, emailadres,paswoord);
	}

    @Override
    public Persoon editPersoon(int id, String voornaam, String familienaam, String emailadres, String paswoord) {
        return null;
    }

    @Override
    public void verwijderPersoonMetId(int id) {
        persoonRepository.delete(zoekPersoonMetId(id));
    }


//    @GetMapping("/edit")
//    public Persoon editPersoon(@RequestParam("id") int id, Model model) {
//        Persoon persoon;
//
//        EntryData entryData = persoonRepository.prepareEntryDataToEdit(id);
//        prepareForm(entryData, model);
//        model.addAttribute("message", "Update or Delete this entry please - or Cancel");
//        return Persoon;
//    }

}