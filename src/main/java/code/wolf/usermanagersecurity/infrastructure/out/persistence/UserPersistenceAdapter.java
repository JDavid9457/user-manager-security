package code.wolf.usermanagersecurity.infrastructure.out.persistence;

import code.wolf.usermanagersecurity.aplication.port.out.*;
import code.wolf.usermanagersecurity.common.PersistenceAdapter;
import code.wolf.usermanagersecurity.domain.model.User;
import code.wolf.usermanagersecurity.infrastructure.config.security.jwt.JwtService;
import code.wolf.usermanagersecurity.infrastructure.exceptions.*;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.entity.UserEntity;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.repositories.UserRepository;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.transformers.UserEntityModelMapper;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.transformers.UserTransformer;
import code.wolf.usermanagersecurity.infrastructure.util.validation.EmailValidation;
import code.wolf.usermanagersecurity.infrastructure.util.validation.PasswordValidation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static code.wolf.usermanagersecurity.infrastructure.util.constants.Messages.*;

@PersistenceAdapter
public class UserPersistenceAdapter implements SaveUserPort, UpdateUSerPort,
        ListUserPort, FindByIdUserPort, DeleteUsertPort, ExistsUserByEmailPort {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailValidation emailValidator;
    private final PasswordValidation passwordValidator;

    public UserPersistenceAdapter(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, EmailValidation emailValidator, PasswordValidation passwordValidator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.emailValidator = emailValidator;
        this.passwordValidator = passwordValidator;
    }


    @Override
    public User saveUser(User user) {
        try {
            validations(user);
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            UserEntity newUser = userRepository.save(UserEntityModelMapper.userToUserEntityForSave(user));
            newUser = addTokeUser(newUser);
            return UserEntityModelMapper.toUserEntityToUser(newUser);
        } catch (EmailDuplicatedException |EmailNotValidException e) {
            throw e;
        }catch (Exception e) {
            throw new UserSaveException(USER_SAVE_ERROR);
        }
    }

    private UserEntity addTokeUser(UserEntity newUser) {
        String token = jwtService.getToken(newUser);
        newUser.setToken(token);
        newUser = userRepository.save(newUser);
        return newUser;
    }

    private void validations(User user) {
        Optional<UserEntity> findUser = userRepository.findByEmail(user.getEmail());
        if (findUser.isPresent()) {
            throw new EmailDuplicatedException(EMAIL_ALREADY_EXISTS);
        }
        if (!emailValidator.isValidEmail(user.getEmail())) {
            throw new EmailNotValidException(EMAIL_INVALID_FORMAT);
        }
        if (!passwordValidator.isValidPassword(user.getPassword())) {
            throw new EmailNotValidException(PASSWORD_INVALID_FORMAT);
        }
    }

    @Override
    public User updateUser(User user, String id) {
        try {
            UserEntity userExists = userRepository.findById(id)
                    .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND + id));
            UserEntity updateUser = userRepository.save(UserEntityModelMapper.userToUserEntityForUpdate(user, userExists));
            return UserEntityModelMapper.toUserEntityToUser(updateUser);
        } catch (UserNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new UserUpdateException(UPDATE_ERROR_WITH_ID + id);
        }
    }

    @Override
    public boolean deleteUser(String id) {
        UserEntity user = userRepository
                .findById(id).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        userRepository.delete(user);
        return true;
    }


    @Override
    public User findByIdUser(String id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        return UserEntityModelMapper.toUserEntityToUser(user);
    }

    @Override
    @Cacheable(cacheNames = "users")
    public List<User> findAllUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        return userEntityList.stream().map(UserTransformer::toEntityToUser).toList();
    }

    @Override
    public boolean existsUserByEmail(String email) {
        Optional<UserEntity> exist = userRepository.findByEmail(email);
        if (exist.isEmpty()) {
            throw new EmailNotFoudException(EMAIL_ALREADY_EXISTS);
        }
        return true;
    }
}
