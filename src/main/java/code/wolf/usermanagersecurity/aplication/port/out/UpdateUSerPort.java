package code.wolf.usermanagersecurity.aplication.port.out;

import code.wolf.usermanagersecurity.domain.model.User;

public interface UpdateUSerPort {
    User updateUser(User user, String id);
}
