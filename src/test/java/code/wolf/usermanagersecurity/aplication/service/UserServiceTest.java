package code.wolf.usermanagersecurity.aplication.service;

import code.wolf.usermanagersecurity.aplication.port.out.*;
import code.wolf.usermanagersecurity.domain.model.Phone;
import code.wolf.usermanagersecurity.domain.model.User;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.entity.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private SaveUserPort saveUserPort;

    @Mock
    private UpdateUSerPort updateUserPort;

    @Mock
    private FindByIdUserPort findByIdUserPort;

    @Mock
    private ExistsUserByEmailPort existsUserByEmailPort;

    @Mock
    private ListUserPort listUserPort;

    @Mock
    private DeleteUsertPort deleteUsertPort;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    Phone phoneOne = Phone.builder()
            .number("3104206988")
            .cityCode("1")
            .contryCode("57")
            .build();

    Phone phoneTwo = Phone.builder()
            .number("3113659874")
            .cityCode("2")
            .contryCode("43")
            .build();

    List<Phone> phones = List.of(phoneOne, phoneTwo);
    User user = User.builder()
            .id("1a950b23-d098-4ea0-83f3-2563ad4bfab8")
            .name("name")
            .email("email")
            .password("password")
            .phones(phones)
            .createdAt(LocalDateTime.now())
            .modifiedAt(LocalDateTime.now())
            .lastLogin(LocalDateTime.now())
            .isActive(true)
            .role(Role.USER)
            .build();


    @Test
    public void testSave() {

        when(saveUserPort.saveUser(user)).thenReturn(user);

        User savedUser = userService.save(user);

        assertEquals(user, savedUser);
        verify(saveUserPort, times(1)).saveUser(user);
    }

    @Test
    public void testUpdateUser() {

        when(updateUserPort.updateUser(user, user.getId())).thenReturn(user);

        User updateUser = userService.update(user, user.getId());

        assertEquals(user, updateUser);
        verify(updateUserPort, times(1)).updateUser(user, user.getId());
    }

    @Test
    public void testFindAll() {

        List<User> users = List.of(user);
        when(listUserPort.findAllUsers()).thenReturn(users);

        List<User> foundUsers = userService.findAll();

        assertEquals(users, foundUsers);
        verify(listUserPort, times(1)).findAllUsers();
    }

    @Test
    public void testFindById() {

        when(findByIdUserPort.findByIdUser(user.getId())).thenReturn(user);

        User foundUser = userService.findById(user.getId());

        assertEquals(user, foundUser);
        verify(findByIdUserPort, times(1)).findByIdUser(user.getId());
    }

    @Test
    public void testExistsByEmail() {

        when(existsUserByEmailPort.existsUserByEmail(user.getEmail())).thenReturn(true);

        boolean exists = userService.existsByEmail(user.getEmail());

        assertTrue(exists);
        verify(existsUserByEmailPort, times(1)).existsUserByEmail(user.getEmail());
    }

    @Test
    public void testDelete() {
        when(deleteUsertPort.deleteUser(user.getId())).thenReturn(true);

        boolean deleted = userService.delete(user.getId());

        assertTrue(deleted);
        verify(deleteUsertPort, times(1)).deleteUser(user.getId());
    }

}