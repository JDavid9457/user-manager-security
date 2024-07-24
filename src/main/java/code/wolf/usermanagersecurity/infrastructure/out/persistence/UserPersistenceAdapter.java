package code.wolf.usermanagersecurity.infrastructure.out.persistence;

import code.wolf.usermanagersecurity.aplication.port.out.*;
import code.wolf.usermanagersecurity.common.PersistenceAdapter;
import code.wolf.usermanagersecurity.domain.model.User;
import code.wolf.usermanagersecurity.infrastructure.config.security.jwt.JwtService;
import code.wolf.usermanagersecurity.infrastructure.exceptions.EmailDuplicatedException;
import code.wolf.usermanagersecurity.infrastructure.exceptions.EmailNotFoudException;
import code.wolf.usermanagersecurity.infrastructure.exceptions.EmailNotValidException;
import code.wolf.usermanagersecurity.infrastructure.exceptions.UserNotFoundException;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.entity.PhoneEntity;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.entity.UserEntity;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.repositories.UserRepository;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.transformers.UserEntityModelMapper;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.transformers.UserTransformer;
import code.wolf.usermanagersecurity.infrastructure.util.validation.EmailValidation;
import code.wolf.usermanagersecurity.infrastructure.util.validation.PasswordValidation;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@PersistenceAdapter
@AllArgsConstructor
public class UserPersistenceAdapter implements SaveUserPort, UpdateUSerPort,
        ListUserPort,FindByIdUserPort,DeleteUsertPort, ExistsUserByEmailPort {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailValidation emailValidator;
    private final PasswordValidation passwordValidator;


    @Override
    public User saveUser(User user) {
        validations(user);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        UserEntity newUser = userRepository.save(UserEntityModelMapper.UserToUserEntityForSave(user));
        userRepository.save(newUser);
        newUser = addTokeUser(newUser);
        return UserEntityModelMapper.toUserEntityToUser(newUser);
    }

    private UserEntity addTokeUser(UserEntity newUser) {
        String token = jwtService.getToken(newUser);
        newUser.setToken(token);
        newUser = userRepository.save(newUser);
        return newUser;
    }

    private void validations(User user) {
        Optional<UserEntity> findUser = userRepository.findByEmail(user.getEmail());
        if(findUser.isPresent()) {
            throw new EmailDuplicatedException("Email ya existe en la base de datos");
        }
        if( !emailValidator.isValidEmail(user.getEmail())){
            throw new EmailNotValidException("Email no cumple con el formato adecuado (aaaaaaa@dominio.cl)");
        }
        if( !passwordValidator.isValidPassword(user.getPassword())){
            throw new EmailNotValidException("Clave no cumple con el formato valido.");
        }
    }

    @Override
    public User updateUser(User user, String id) {
        UserEntity updateUser = userRepository.findById(id)
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
        UserEntity user = userRepository
                .findById(id).orElseThrow(() -> new UserNotFoundException("Merchant not found"));
        userRepository.delete(user);
        return true;
    }


    @Override
    public User findByIdUser(String id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("not found user"));
        return UserEntityModelMapper.toUserEntityToUser(user);
    }

    @Override
    public List<User> findAllUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        return userEntityList.stream().map(UserTransformer::toEntityToUser).toList();
    }

    @Override
    public boolean existsUserByEmail(String email) {
        Optional<UserEntity> exist = userRepository.findByEmail(email);
        if(exist.isEmpty()) {
            throw new EmailNotFoudException("Email ya existe en la base de datos");
        }
        return true;
    }
}
