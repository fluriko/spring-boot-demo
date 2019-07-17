package mate.academy.controller;

import mate.academy.model.Category;
import mate.academy.service.CategoryService;
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
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @CrossOrigin("http://localhost:4200")
    @RequestMapping(value = "/api/category", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @CrossOrigin("http://localhost:4200")
    @RequestMapping(value = "/api/category/{id}", method = RequestMethod.GET)
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        return categoryService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @CrossOrigin("http://localhost:4200")
    @RequestMapping(value = "/api/category", method = RequestMethod.POST)
    public ResponseEntity<Category> add(@RequestBody Category category) {
        return categoryService.save(category)
                .map(c -> ResponseEntity.created(toUri(c.getId())).body(c))
                .orElseGet(ResponseEntity.status(HttpStatus.CONFLICT)::build);
    }

    @CrossOrigin("http://localhost:4200")
    @RequestMapping(value = "/api/category/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        category.setId(id);
        return categoryService.update(category)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.status(HttpStatus.CONFLICT)::build);
    }

    @CrossOrigin("http://localhost:4200")
    @RequestMapping(value = "/api/category/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private URI toUri(Long id) {
        return URI.create(String.format("/api/ /%s", id));
    }
}
