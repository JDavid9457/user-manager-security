package code.wolf.usermanagersecurity.aplication.port.out;

import code.wolf.usermanagersecurity.domain.model.User;

public interface FindByEmailPort {
    User findByEmailUser(String email);
}
