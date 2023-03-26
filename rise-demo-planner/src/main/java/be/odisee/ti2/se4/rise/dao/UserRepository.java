package be.odisee.ti2.se4.rise.dao;

import be.odisee.ti2.se4.rise.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
