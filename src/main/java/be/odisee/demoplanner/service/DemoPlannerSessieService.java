package be.odisee.demoplanner.service;

import be.odisee.demoplanner.domain.*;
import be.odisee.demoplanner.utilities.RolNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public interface DemoPlannerSessieService {

    public List<Persoon> geefAllePersonen();

    public Persoon voegPersoonToe(String voornaam, String familienaam, String emailadres, String paswoord);

    public Persoon zoekPersoonMetId(int id);

	public Persoon zoekPersoonMetEmail(String email);
    public Persoon updatePersoon(Persoon persoon);

    public void verwijderPersoonMetId(int id);

    public List<Demo> geefAlleDemos();

    public Demo zoekDemoMetId(int id);
    public Demo voegDemoToe(String naam, String adres, String status);

    Demo updateDemo(Demo demo);

    void verwijderDemoMetId(int id);

    public List<Datum> geefAlleDatums();

    public Datum zoekDatumMetId(int id);

    public Datum voegDatumToe(LocalDateTime naam, LocalDateTime adres, String status, Demo datums);

    Datum updateDatum(Datum datum);

     void verwijderDatumMetId(int id);

    public List<Planning> geefAllePlanningen();
    public Planning zoekPlanningMetId(int id);

    public Planning updatePlanning(Planning planning);
    public Planning voegPlanningToe(String status, int datum_id, int ambassadeur_id);

//    public Rol voegRolToe(String type, int sessieId, int persoonId, String usernaam) throws RolNotFoundException;

//    public Rol zoekRolMetId(int id);

}