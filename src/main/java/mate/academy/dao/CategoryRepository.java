package mate.academy.dao;

import mate.academy.model.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Override
    @EntityGraph(value = "Category.products")
    List<Category> findAll();
}
