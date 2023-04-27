package be.odisee.demoplanner.dao;

import be.odisee.demoplanner.domain.Datum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatumRepository extends JpaRepository<Datum, Integer> {
}
