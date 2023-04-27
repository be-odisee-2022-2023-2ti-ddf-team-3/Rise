package be.odisee.demoplanner.service;

import be.odisee.demoplanner.dao.*;
import be.odisee.demoplanner.domain.*;
import be.odisee.demoplanner.utilities.RolNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    @Autowired
    private PlanningRepository planningRepository;
    public List<Planning> geefAllePlanningen() {
        return planningRepository.findAll();
    }
    @Override
    public Planning updatePlanning(Planning planning) {
        return planningRepository.save(planning);
    }
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false)
    public Planning zoekPlanningMetId(int id){
        Planning planning;

        Optional<Planning> optionalPlanning = planningRepository.findById(id);
        if ( optionalPlanning.isPresent() ) planning = optionalPlanning.get();
        else planning = null; // in dat geval hebben we geen plannning met dat id gevonden
        return planning;
    }
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false)
    public Planning voegPlanningToe(String status, int datum_id, int ambassadeur_id) {

        return planningRepository.save( createPlanning(status, datum_id, ambassadeur_id) );
    }

    private Planning createPlanning( String status,int datum_id, int ambassadeur_id) {

        return new Planning(status, datum_id, ambassadeur_id );
    }

//    @Autowired
//    private RolRepository rolRepository;
//
//    public Rol zoekRolMetId(int id) {
//        Rol rol = null;
//
//        Optional<Rol> optionalRol = rolRepository.findById(id);
//        if ( optionalRol.isPresent() ) rol = optionalRol.get();
//        return rol;
//    }

//    @Override
//    public Rol voegRolToe(String type, int sessieId, int persoonId, String usernaam) throws RolNotFoundException {
////        Sessie deSessie = zoekSessieMetId(sessieId);
//        Persoon dePersoon = zoekPersoonMetId(persoonId);
//        Rol deRol = dePersoon.voegRolToe(type, "actief", usernaam);
//        deRol = rolRepository.save(deRol);
//        return deRol;
//    }
}