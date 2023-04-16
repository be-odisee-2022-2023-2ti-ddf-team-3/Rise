package be.odisee.demoplanner.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import be.odisee.demoplanner.domain.Demo;

public interface DemoRepository extends JpaRepository<Demo, Integer> {
}
