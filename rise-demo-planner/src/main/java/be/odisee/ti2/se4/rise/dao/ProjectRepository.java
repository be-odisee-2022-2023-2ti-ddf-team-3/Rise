package be.odisee.ti2.se4.rise.dao;

import be.odisee.ti2.se4.rise.domain.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    /**
     * The default findById would return Optional<Project>
     * We want a Project object as return
     * therefore we override this method
     * @param id
     * @return
     */
    public Project findById(long id);

    public List<Project> findAllByOrderByName();
}
