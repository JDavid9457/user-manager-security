package code.wolf.usermanagersecurity.aplication.port.out;

import code.wolf.usermanagersecurity.domain.model.User;

import java.util.List;

public interface ListUserPort {
    List<User> findAllUsers();
}
