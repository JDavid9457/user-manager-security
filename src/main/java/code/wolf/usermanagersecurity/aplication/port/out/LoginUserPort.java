package code.wolf.usermanagersecurity.aplication.port.out;

import code.wolf.usermanagersecurity.domain.model.User;

public interface LoginUserPort {
    User login(User user);
}
