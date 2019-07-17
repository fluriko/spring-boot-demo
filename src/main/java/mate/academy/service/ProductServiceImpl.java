package mate.academy.service;

import mate.academy.dao.ProductRepository;
import mate.academy.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<List<Product>> getAll() {
        return Optional.of(productRepository.findAll());
    }

    @Override
    public Optional<Product> create(Product product) {
        return Optional.of(productRepository.save(product));
    }

    @Override
    public Optional<Product> update(Product product) {
        return Optional.of(productRepository.save(product));
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
