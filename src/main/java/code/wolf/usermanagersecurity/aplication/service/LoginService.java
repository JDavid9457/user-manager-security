package code.wolf.usermanagersecurity.aplication.service;

import code.wolf.usermanagersecurity.aplication.port.in.LoginPort;
import code.wolf.usermanagersecurity.common.UseCase;
import code.wolf.usermanagersecurity.domain.model.User;
@UseCase
public class LoginService implements LoginPort {

    private final LoginPort loginPort;

    public LoginService(LoginPort loginPort) {
        this.loginPort = loginPort;
    }

    @Override
    public User loginUser(User user) {
        return loginPort.loginUser(user);
    }
}
