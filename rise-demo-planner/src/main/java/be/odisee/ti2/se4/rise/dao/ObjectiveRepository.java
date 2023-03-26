package be.odisee.ti2.se4.rise.dao;

import be.odisee.ti2.se4.rise.domain.Objective;
import org.springframework.data.repository.CrudRepository;

public interface ObjectiveRepository extends CrudRepository<Objective, Long> {

    /**
     * The default findById would return Optional<Objective>
     * We want a Objective object as return
     * therefore we override this method
     * @param id
     * @return
     */
    public Objective findById(long id);

}
