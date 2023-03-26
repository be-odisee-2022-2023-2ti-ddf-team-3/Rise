package be.odisee.ti2.se4.rise.dao;

import be.odisee.ti2.se4.rise.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    /**
     * Look up a category based on its unique name
     */
    public Category findCategoryByName(String name);

    /**
     * List all categories, order alphabetically by name
     */
    public List<Category> findAllByOrderByName();
}
