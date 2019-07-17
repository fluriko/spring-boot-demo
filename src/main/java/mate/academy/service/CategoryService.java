package mate.academy.service;

import mate.academy.model.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> getAll();

    Optional<Category> getById(Long id);

    Optional<Category> save(Category category);

    Optional<Category> update(Category category);

    void deleteById(Long id);
}
