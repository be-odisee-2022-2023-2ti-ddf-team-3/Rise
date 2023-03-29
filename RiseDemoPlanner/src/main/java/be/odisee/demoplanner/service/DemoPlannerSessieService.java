package be.odisee.demoplanner.service;

import be.odisee.demoplanner.domain.*;
import java.util.List;

public interface DemoPlannerSessieService {

    public List<Persoon> geefAllePersonen();

    public Persoon voegPersoonToe(String voornaam, String familienaam, String emailadres, String paswoord);

    public Persoon zoekPersoonMetId(int id);

	public Persoon zoekPersoonMetEmail(String email);
}