package code.wolf.usermanagersecurity.aplication.service;

import code.wolf.usermanagersecurity.aplication.port.in.UserPort;
import code.wolf.usermanagersecurity.aplication.port.out.*;
import code.wolf.usermanagersecurity.common.UseCase;
import code.wolf.usermanagersecurity.domain.model.User;

import java.util.List;

@UseCase
public class UserService implements UserPort {
    private final SaveUserPort saveUserPort;
    private final UpdateUSerPort updateUserPort;
    private final FindByIdUserPort findByIdUserPort;
    private final ListUserPort listUserPort;
    private final FindByEmailPort findByEmailPort;
    private final ExistsByEmailPort existsByEmailPort;
    private final DeleteUsertPort deleteUsertPort;


    public UserService(SaveUserPort saveUserPort, UpdateUSerPort updateUserPort, FindByIdUserPort findByIdUserPort,
                       ListUserPort listUserPort, FindByEmailPort findByEmailPort,
                       ExistsByEmailPort existsByEmailPort, DeleteUsertPort deleteUsertPort) {
        this.saveUserPort = saveUserPort;
        this.updateUserPort = updateUserPort;
        this.findByIdUserPort = findByIdUserPort;
        this.listUserPort = listUserPort;
        this.findByEmailPort = findByEmailPort;
        this.existsByEmailPort = existsByEmailPort;
        this.deleteUsertPort = deleteUsertPort;
    }

    @Override
    public User save(User user) {
        return saveUserPort.saveUser(user);
    }

    @Override
    public User update(User user, String id) {
        return updateUserPort.updateUser(user, id);
    }

    @Override
    public List<User> findAll() {
        return listUserPort.findAllUsers();
    }

    @Override
    public User findById(String id) {
        return findByIdUserPort.findByIdUser(id);
    }

    @Override
    public User findByEmail(String email) {
        return findByEmailPort.findByEmailUser(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return existsByEmailPort.existsByEmailUser(email);
    }

    @Override
    public boolean delete(String id) {
        return deleteUsertPort.deleteUser(id);
    }
}
