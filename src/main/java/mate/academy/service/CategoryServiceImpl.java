package mate.academy.service;

import mate.academy.dao.CategoryRepository;
import mate.academy.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Optional<Category> save(Category category) {
        return Optional.ofNullable(categoryRepository.save(category));
    }

    @Override
    public Optional<Category> update(Category category) {
        return Optional.ofNullable(categoryRepository.save(category));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
