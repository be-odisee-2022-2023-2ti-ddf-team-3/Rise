package be.odisee.ti2.se4.rise;

import be.odisee.ti2.se4.rise.dao.CategoryRepository;
import be.odisee.ti2.se4.rise.dao.ObjectiveRepository;
import be.odisee.ti2.se4.rise.dao.ProjectRepository;
import be.odisee.ti2.se4.rise.domain.Category;
import be.odisee.ti2.se4.rise.domain.Objective;
import be.odisee.ti2.se4.rise.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


/**
 * Database initializer that populates the database with some
 * initial data.
 *
 * This component is started only when app.db-init property is set to true
 */
@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
public class RiseApplicationInitDB implements CommandLineRunner {

    @Autowired
    CategoryRepository categoryRepository;
    
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ObjectiveRepository objectiveRepository;

    @Override
    public void run(String... args) throws Exception {

        List<Category> categories = Arrays.asList(
                new Category(1, "OVHD"),
                new Category(2, "NETW"),
                new Category(3, "PROSP"),
                new Category(4, "FULF"),
                new Category(5, "REND"),
                new Category(6, "TRAVEL")
        );

        for (Category category : categories) {
            categoryRepository.save(category);
        };

        List<Project> projects = Arrays.asList(
                new Project(1, "General", categoryRepository.findCategoryByName("OVHD")),
                new Project(2, "Infrastructure", categoryRepository.findCategoryByName("OVHD") ),
                new Project(3, "Jazzzolder", categoryRepository.findCategoryByName("NETW") ),
                new Project(4, "Jazzathome", categoryRepository.findCategoryByName("NETW") ),
                new Project(5, "Jazzcontest", categoryRepository.findCategoryByName("NETW") ),
                new Project(6, "Odisee", categoryRepository.findCategoryByName("FULF") ),
                new Project(7, "EMS", categoryRepository.findCategoryByName("FULF") ),
                new Project(8, "Syntra-AB", categoryRepository.findCategoryByName("FULF") ),
                new Project(9, "SBM", categoryRepository.findCategoryByName("FULF") ),
                new Project(10, "Microservices", categoryRepository.findCategoryByName("REND") ),
                new Project(11, "Odisee", categoryRepository.findCategoryByName("TRAVEL") )
        );

        for (Project project: projects) {
            projectRepository.save(project);
        }

        List<Objective> objectives = Arrays.asList(
                new Objective(1, "Omzet zelfstandig"),
                new Objective(2, "Omzet digitaal"),
                new Objective(3, "Digitale aanwezigheid"),
                new Objective(4, "Talentdoelgroepen"),
                new Objective(5, "Automatisering"),
                new Objective(6, "Lean agile devops"),
                new Objective(7, "DX in training"),
                new Objective(8, "Training topics")
        );

        for (Objective objective: objectives) {
            objectiveRepository.save(objective);
        }
    }
}
