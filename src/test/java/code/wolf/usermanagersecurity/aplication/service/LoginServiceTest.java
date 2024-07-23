package code.wolf.usermanagersecurity.aplication.service;

import code.wolf.usermanagersecurity.aplication.port.out.LoginUserPort;
import code.wolf.usermanagersecurity.domain.model.Phone;
import code.wolf.usermanagersecurity.domain.model.User;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class LoginServiceTest {

    @Mock
    private LoginUserPort loginUserPort;

    @InjectMocks
    private LoginService loginService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testLoginUser() {

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

        when(loginUserPort.login(user)).thenReturn(user);

        User actualUser = loginService.loginUser(user);

        assertEquals(user, actualUser);
    }
}