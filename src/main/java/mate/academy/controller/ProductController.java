package mate.academy.controller;

import mate.academy.model.Product;
import mate.academy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @CrossOrigin("http://localhost:4200")
    @RequestMapping(value = "/api/product", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAll().orElseGet(Collections::emptyList));
    }

    @CrossOrigin("http://localhost:4200")
    @RequestMapping(value = "/api/product/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return productService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @CrossOrigin("http://localhost:4200")
    @RequestMapping(value = "/api/product", method = RequestMethod.POST)
    public ResponseEntity<Product> add(@RequestBody Product product) {
        return productService.create(product)
                .map(p -> ResponseEntity.created(toUri(p.getId())).body(p))
                .orElseGet(ResponseEntity.status(HttpStatus.CONFLICT)::build);
    }

    @CrossOrigin("http://localhost:4200")
    @RequestMapping(value = "/api/product/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        return productService.update(product)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.status(HttpStatus.CONFLICT)::build);
    }

    @CrossOrigin("http://localhost:4200")
    @RequestMapping(value = "/api/product/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private URI toUri(Long id) {
        return URI.create(String.format("/api/ /%s", id));
    }
}
