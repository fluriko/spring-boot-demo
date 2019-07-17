package mate.academy.service;

import mate.academy.dao.UserRepository;
import mate.academy.model.Role;
import mate.academy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public Optional<User> create(User user) {
        return Optional.ofNullable(userRepository.save(user));
    }

    @Override
    public Optional<User> create(User user, List<Role> roles) {
        user.setRoles(roles);
        return Optional.ofNullable(userRepository.save(user));
    }

    @Override
    public Optional<User> createWithRoleUser(User user) {
        user.setRoles(Collections.singletonList(Role.ofUser()));
        return Optional.ofNullable(userRepository.save(user));
    }

    @Override
    public Optional<User> update(User user) {
        return Optional.ofNullable(userRepository.save(user));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<List<User>> getAll() {
        return Optional.ofNullable(userRepository.findAll());
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

}
