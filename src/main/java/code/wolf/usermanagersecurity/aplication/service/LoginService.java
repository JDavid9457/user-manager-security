package code.wolf.usermanagersecurity.aplication.service;

import code.wolf.usermanagersecurity.aplication.port.in.LoginPort;
import code.wolf.usermanagersecurity.aplication.port.out.LoginUserPort;
import code.wolf.usermanagersecurity.common.UseCase;
import code.wolf.usermanagersecurity.domain.model.User;

@UseCase
public class LoginService implements LoginPort {
    private final LoginUserPort loginUserPort;

    public LoginService(LoginUserPort loginUserPort) {
        this.loginUserPort = loginUserPort;
    }

    @Override
    public User loginUser(User user) {
        return loginUserPort.login(user);
    }


}
