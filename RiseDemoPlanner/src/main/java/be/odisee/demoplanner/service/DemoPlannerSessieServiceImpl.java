package be.odisee.demoplanner.service;

import be.odisee.demoplanner.domain.*;
import be.odisee.demoplanner.dao.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

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
    public Persoon updatePersoon(Persoon persoon) {
        return persoonRepository.save(persoon);
    }

    @Override
    public void verwijderPersoonMetId(int id) {
        persoonRepository.delete(zoekPersoonMetId(id));
    }

    @Autowired
    private DemoRepository demoRepository;
    public List<Demo> geefAlleDemos() {
        return demoRepository.findAll();
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=false)
    public Demo zoekDemoMetId(int id){
        Demo demo;

        Optional<Demo> optionalDemo = demoRepository.findById(id);
        if ( optionalDemo.isPresent() ) demo = optionalDemo.get();
        else demo = null; // in dat geval hebben we geen persoon met dat id gevonden
        return demo;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=false)
    public Demo voegDemoToe(String naam, String adres, String status) {

        return demoRepository.save( createDemo(naam,adres,status) );
    }

    private Demo createDemo( String naam, String adres, String status) {

        return new Demo( naam, adres,status );
    }

    @Override
    public Demo updateDemo(Demo demo) {
        return demoRepository.save(demo);
    }

    @Override
    public void verwijderDemoMetId(int id) {
        demoRepository.delete(zoekDemoMetId(id));
    }

    @Autowired
    private DatumRepository datumRepository;
    public List<Datum> geefAlleDatums() {
        return datumRepository.findAll();
    }

    public Map<Demo, List<Datum>> geefDemoMetDatums() {
        Map<Demo, List<Datum>> DemosMetDatums = new LinkedHashMap<Demo, List<Datum>>();
        return DemosMetDatums;
//                datumRepository.findAll();
    }


    @Transactional(propagation= Propagation.REQUIRED,readOnly=false)
    public Datum zoekDatumMetId(int id){
        Datum datum;

        Optional<Datum> optionalDatum = datumRepository.findById(id);
        if ( optionalDatum.isPresent() ) datum = optionalDatum.get();
        else datum = null; // in dat geval hebben we geen persoon met dat id gevonden
        return datum;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=false)
    public Datum voegDatumToe( LocalDateTime start, LocalDateTime einde, String status, Demo demo ) {

        return datumRepository.save( createDatum( start, einde, status, demo ));
    }

    private Datum createDatum( LocalDateTime start, LocalDateTime einde, String status, Demo demo ) {

        return new Datum( start, einde, status, demo );
    }

    @Override
    public Datum updateDatum(Datum datum) {
        return datumRepository.save(datum);
    }

    @Override
    public void verwijderDatumMetId(int id) {
        datumRepository.delete(zoekDatumMetId(id));
    }

}