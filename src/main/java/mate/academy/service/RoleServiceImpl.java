package mate.academy.service;

import mate.academy.dao.RoleRepository;
import mate.academy.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Role> create(Role role) {
        return Optional.of(roleRepository.save(role));
    }

    @Override
    public Optional<List<Role>> getAll() {
        return Optional.of(roleRepository.findAll());
    }
}
