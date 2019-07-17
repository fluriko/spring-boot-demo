package mate.academy.dao;

import mate.academy.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph("User.roles")
    Optional<User> getById(Long id);

    @EntityGraph("User.roles")
    List<User> findAll();

    @EntityGraph("User.roles")
    Optional<User> getByEmail(String email);
}
