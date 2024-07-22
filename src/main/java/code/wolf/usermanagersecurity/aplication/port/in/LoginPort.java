package code.wolf.usermanagersecurity.aplication.port.in;

import code.wolf.usermanagersecurity.domain.model.User;

public interface LoginPort {
    User loginUser(User user);
}
