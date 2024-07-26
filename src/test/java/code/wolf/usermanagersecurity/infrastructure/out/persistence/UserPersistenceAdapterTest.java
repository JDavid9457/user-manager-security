package code.wolf.usermanagersecurity.infrastructure.out.persistence;

import code.wolf.usermanagersecurity.domain.model.Phone;
import code.wolf.usermanagersecurity.domain.model.User;
import code.wolf.usermanagersecurity.infrastructure.config.security.jwt.JwtService;
import code.wolf.usermanagersecurity.infrastructure.exceptions.*;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.entity.PhoneEntity;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.entity.Role;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.entity.UserEntity;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.repositories.UserRepository;
import code.wolf.usermanagersecurity.infrastructure.util.validation.EmailValidation;
import code.wolf.usermanagersecurity.infrastructure.util.validation.PasswordValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserPersistenceAdapterTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private EmailValidation emailValidator;

    @Mock
    private PasswordValidation passwordValidator;

    @InjectMocks
    private UserPersistenceAdapter userPersistenceAdapter;

    private User testUser;
    private UserEntity testUserEntity;
    private Phone testPhoneEntity;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        testPhoneEntity = Phone.builder()
                .id(1L)
                .number("31549921")
                .cityCode("57")
                .contryCode("18")
                .build();

        List<PhoneEntity> listPhone = new ArrayList<>();

        testUser = User.builder()
                .id("3930cdb6-862a-4d4f-8eaa-5bbac5f615d7")
                .name("ricardo Rodriguez")
                .email("ricardo@riguez.org")
                .password("hunter2A*")
                .phones(List.of(Phone
                        .builder()
                        .id(1L)
                        .number("123456789")
                        .cityCode("01")
                        .contryCode("502")
                        .build()))
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .token("eyJhbGciOiJIUzI1NiJ9")
                .isActive(true)
                .role(Role.USER)
                .build();

        testUserEntity = new UserEntity();
        testUserEntity.setEmail("ricardo@riguez.org");
        testUserEntity.setPassword("hunter2A*");
        testUserEntity.setPhones(listPhone);
        testUserEntity.setCreatedAt(LocalDateTime.now());
        testUserEntity.setModifiedAt(LocalDateTime.now());
        testUserEntity.setLastLogin(LocalDateTime.now());
        testUserEntity.setToken("eyJhbGciOiJIUzI1NiJ9");
        testUserEntity.setActive(true);
        testUserEntity.setRole(Role.USER);

    }

    @Test
    public void testSaveUserSuccess() {

        when(userRepository.findByEmail(testUser.getEmail())).thenReturn(Optional.empty());
        when(emailValidator.isValidEmail(testUser.getEmail())).thenReturn(true);
        when(passwordValidator.isValidPassword(testUser.getPassword())).thenReturn(true);
        when(passwordEncoder.encode(testUser.getPassword())).thenReturn("hunter2A*");
        when(userRepository.save(any(UserEntity.class))).thenReturn(testUserEntity);
        when(jwtService.getToken(any(UserEntity.class))).thenReturn("eyJhbGciOiJIUzI1NiJ9");

        User savedUser = userPersistenceAdapter.saveUser(testUser);

        assertEquals("eyJhbGciOiJIUzI1NiJ9", savedUser.getToken());
        assertEquals("ricardo@riguez.org", savedUser.getEmail());
        assertEquals("hunter2A*", savedUser.getPassword());
    }

    @Test
    public void testSaveUserEmailAlreadyExists() {

        when(userRepository.findByEmail(testUser.getEmail())).thenReturn(Optional.of(testUserEntity));

        assertThrows(EmailDuplicatedException.class, () -> {
            userPersistenceAdapter.saveUser(testUser);
        });

        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    public void testSaveUserInvalidEmail() {
        when(userRepository.findByEmail(testUser.getEmail())).thenReturn(Optional.empty());
        when(emailValidator.isValidEmail(testUser.getEmail())).thenReturn(false);

        assertThrows(EmailNotValidException.class, () -> {
            userPersistenceAdapter.saveUser(testUser);
        });

        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    public void testSaveUserInvalidPassword() {
        when(userRepository.findByEmail(testUser.getEmail())).thenReturn(Optional.empty());
        when(emailValidator.isValidEmail(testUser.getEmail())).thenReturn(true);
        when(passwordValidator.isValidPassword(testUser.getPassword())).thenReturn(false);

        assertThrows(EmailNotValidException.class, () -> {
            userPersistenceAdapter.saveUser(testUser);
        });
        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    public void testUpdateUserThrowsException() {
        String userId = "1a950b23-d098-4ea0-83f3-2563ad4bfab8";
        UserEntity existingUserEntity = new UserEntity();

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUserEntity));
        doThrow(new RuntimeException("Simulated exception")).when(userRepository).save(existingUserEntity);

        assertThrows(UserUpdateException.class, () -> {
            userPersistenceAdapter.updateUser(testUser, userId);
        });
    }

    @Test
    public void testDeleteUserSuccess() {
        String userId = "1a950b23-d098-4ea0-83f3-2563ad4bfab8";
        UserEntity existingUserEntity = new UserEntity();
        existingUserEntity.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUserEntity));

        boolean result = userPersistenceAdapter.deleteUser(userId);

        assertTrue(result);
        verify(userRepository, times(1)).delete(existingUserEntity);
    }

    @Test
    public void testDeleteUserNotFound() {
        String userId = "1a950b23-d098-4ea0-83f3-2563ad4bfab8";

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userPersistenceAdapter.deleteUser(userId);
        });

        verify(userRepository, never()).delete(any(UserEntity.class));
    }

    @Test
    public void testFindByIdUserSuccess() {
        String userId = "1a950b23-d098-4ea0-83f3-2563ad4bfab8";
        UserEntity findByUserEntity = new UserEntity();
        findByUserEntity.setId(userId);
        findByUserEntity.setPhones(new ArrayList<>());

        when(userRepository.findById(userId)).thenReturn(Optional.of(findByUserEntity));

        User foundUser = userPersistenceAdapter.findByIdUser(userId);

        assertEquals(userId, foundUser.getId());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void testFindByIdUserNotFound() {
        String userId = "1a950b23-d098-4ea0-83f3-2563ad4bfab8";

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userPersistenceAdapter.findByIdUser(userId);
        });

        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void testExistsUserByEmailSuccess() {
        String email = "ricardo@riguez.org";
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(userEntity));

        boolean exists = userPersistenceAdapter.existsUserByEmail(email);

        assertTrue(exists);
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    public void testExistsUserByEmailNotFound() {
        String email = "ricardo@riguez.org";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(EmailNotFoudException.class, () -> {
            userPersistenceAdapter.existsUserByEmail(email);
        });
        verify(userRepository, times(1)).findByEmail(email);
    }
}