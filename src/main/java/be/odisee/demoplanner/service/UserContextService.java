package be.odisee.demoplanner.service;

import be.odisee.demoplanner.domain.Persoon;

public interface UserContextService {

    public Persoon getAuthenticatedPersoon();
}
