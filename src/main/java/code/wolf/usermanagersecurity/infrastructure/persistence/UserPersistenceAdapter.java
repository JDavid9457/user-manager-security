package code.wolf.usermanagersecurity.infrastructure.persistence;

import code.wolf.usermanagersecurity.aplication.port.out.*;
import code.wolf.usermanagersecurity.common.PersistenceAdapter;
import code.wolf.usermanagersecurity.domain.model.User;
import code.wolf.usermanagersecurity.infrastructure.config.security.jwt.JwtService;
import code.wolf.usermanagersecurity.infrastructure.exceptions.UserNotFoundException;
import code.wolf.usermanagersecurity.infrastructure.persistence.entity.PhoneEntity;
import code.wolf.usermanagersecurity.infrastructure.persistence.entity.Role;
import code.wolf.usermanagersecurity.infrastructure.persistence.entity.UserEntity;
import code.wolf.usermanagersecurity.infrastructure.persistence.repositories.UserRepository;
import code.wolf.usermanagersecurity.infrastructure.persistence.transformers.UserMapper;
import code.wolf.usermanagersecurity.infrastructure.persistence.transformers.UserTransformer;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@PersistenceAdapter
public class UserPersistenceAdapter implements SaveUserPort, UpdateUSerPort, ListUserPort, FindByIdUserPort,
        FindByEmailPort, ExistsByEmailPort, DeleteUsertPort {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final RestTemplateAutoConfiguration restTemplateAutoConfiguration;

    public UserPersistenceAdapter(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, RestTemplateAutoConfiguration restTemplateAutoConfiguration) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.restTemplateAutoConfiguration = restTemplateAutoConfiguration;
    }

    @Override
    public User saveUser(User user) {
        UserEntity saveUser = userRepository.save(UserMapper.toUserEntitySave(user));
        saveUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return UserTransformer.toEntityToUser(saveUser);
    }

    @Override
    public User updateUser(User user, String id) {
        UserEntity updateUser = userRepository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new UserNotFoundException("usuario no encontrado"));

        List<PhoneEntity> listPhone = user.getPhones().stream().map(p ->
                PhoneEntity.builder()
                        .number(p.getNumber())
                        .cityCode(p.getCityCode())
                        .contryCode(p.getContryCode()).build()).toList();
        updateUser.setUsername(user.getName());
        updateUser.setEmail(user.getEmail());
        updateUser.setPassword(passwordEncoder.encode(user.getPassword()));
        updateUser.setPhones(listPhone);
        updateUser.setModifiedAt(LocalDateTime.now());
        updateUser.setLastLogin(LocalDateTime.now());
        updateUser.setActive(false);

        UserEntity save = userRepository.save(updateUser);

        return UserTransformer.toEntityToUser(save);
    }

    @Override
    public boolean deleteUser(String id) {
        return false;
    }

    @Override
    public boolean existsByEmailUser(String email) {
        return false;
    }

    @Override
    public User findByEmailUser(String email) {
        return null;
    }

    @Override
    public User findByIdUser(String id) {
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        return List.of();
    }


}
