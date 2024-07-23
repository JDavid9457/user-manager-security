package code.wolf.usermanagersecurity.aplication.port.in;

import code.wolf.usermanagersecurity.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserPort {
    User save(User user);

    User update(User user, String id);

    List<User> findAll();

    User findById(String id);

    boolean existsByEmail(String email);

    boolean delete(String id);
}
