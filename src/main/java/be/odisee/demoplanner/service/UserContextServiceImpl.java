package be.odisee.demoplanner.service;

import be.odisee.demoplanner.dao.PersoonRepository;
import be.odisee.demoplanner.domain.Persoon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service("UserContextService")
public class UserContextServiceImpl implements UserContextService {

    @Autowired
    protected DemoPlannerSessieService demoPlannerSessieService =null; // ready for dependency injection

    @Override
    public Persoon getAuthenticatedPersoon() {
    
    	UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String username = principal.getUsername();

	    Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

	    Persoon thePersoon = demoPlannerSessieService.zoekPersoonMetEmail(username);
	    return thePersoon;
    }
}
