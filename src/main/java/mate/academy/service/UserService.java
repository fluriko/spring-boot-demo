package mate.academy.service;

import mate.academy.model.Role;
import mate.academy.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getById(Long id);

    Optional<User> create(User user);

    Optional<User> create(User user, List<Role> roles);

    Optional<User> createWithRoleUser(User user);

    Optional<User> update(User user);

    void deleteById(Long id);

    Optional<List<User>> getAll();

    Optional<User> getByEmail(String email);
}
