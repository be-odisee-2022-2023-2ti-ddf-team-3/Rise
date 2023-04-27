package be.odisee.demoplanner.dao;

import be.odisee.demoplanner.domain.Persoon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersoonRepository extends JpaRepository<Persoon, Integer> {
	Persoon findByEmailadres(String email);

}
