package be.odisee.demoplanner.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import be.odisee.demoplanner.domain.Persoon;

public interface PersoonRepository extends JpaRepository<Persoon, Integer> {
	Persoon findByEmailadres(String email);

}
