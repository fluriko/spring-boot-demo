package mate.academy.service;

import mate.academy.model.Role;
import java.util.List;
import java.util.Optional;

public interface RoleService {

    Optional<Role> create(Role role);

    Optional<List<Role>> getAll();
}
