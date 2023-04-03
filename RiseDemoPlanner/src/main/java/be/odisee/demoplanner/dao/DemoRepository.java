package be.odisee.demoplanner.dao;

import be.odisee.demoplanner.domain.Demo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemoRepository extends JpaRepository<Demo, Integer> {

}
