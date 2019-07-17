package mate.academy.service;

import mate.academy.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Optional<Product> getById(Long id);

    Optional<List<Product>> getAll();

    Optional<Product> create(Product product);

    Optional<Product> update(Product product);

    void deleteById(Long id);
}
