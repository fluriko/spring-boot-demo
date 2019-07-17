package mate.academy.controller;

import mate.academy.controller.model.UserExt;
import mate.academy.model.User;
import mate.academy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin("http://localhost:4200")
    @RequestMapping(value = "/api/user/by-email", method = RequestMethod.GET)
    public ResponseEntity<User> findByEmail(@RequestParam("email") String email) {
        return userService.getByEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @CrossOrigin("http://localhost:4200")
    @RequestMapping(value = "/api/user", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAll().orElseGet(Collections::emptyList));
    }

    @CrossOrigin("http://localhost:4200")
    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return userService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @CrossOrigin("http://localhost:4200")
    @RequestMapping(value = "/api/user", method = RequestMethod.POST)
    public ResponseEntity<User> add(@RequestBody UserExt userExt) {
        return userService.create(User.of(userExt))
                .map(u -> ResponseEntity.created(toUri(u.getId())).body(u))
                .orElseGet(ResponseEntity.status(HttpStatus.CONFLICT)::build);
    }

    @CrossOrigin("http://localhost:4200")
    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return userService.update(user)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.status(HttpStatus.CONFLICT)::build);
    }

    @CrossOrigin("http://localhost:4200")
    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private URI toUri(Long id) {
        return URI.create(String.format("/api/user/%s", id));
    }
}
