package code.wolf.usermanagersecurity.aplication.port.in;

import code.wolf.usermanagersecurity.domain.model.User;

import java.util.List;

public interface UserPort {
    User save(User user);

    User update(User user, String id);

    List<User> findAll();

    User findById(String id);

    User findByEmail(String email);

    boolean existsByEmail(String email);

    boolean delete(String id);
}
