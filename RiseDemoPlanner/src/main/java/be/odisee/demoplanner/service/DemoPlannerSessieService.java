package be.odisee.demoplanner.service;

import be.odisee.demoplanner.domain.*;
import java.util.List;

public interface DemoPlannerSessieService {

    public List<Persoon> geefAllePersonen();

    public Persoon voegPersoonToe(String voornaam, String familienaam, String emailadres, String paswoord);

    public Persoon zoekPersoonMetId(int id);

	public Persoon zoekPersoonMetEmail(String email);
    public Persoon editPersoon(int id, String voornaam, String familienaam, String emailadres, String paswoord);

    public void verwijderPersoonMetId(int id);

    public List<Demo> geefAlleDemos();

    public Demo voegDemoToe(String naam, String adres, String status);

    public Demo zoekDemoMetId(int id);

    public Demo zoekDemoMetNaam(String email);
    public Demo editDemo(int id, String naam, String adres, String status);

    public void verwijderDemoMetId(int id);
}

