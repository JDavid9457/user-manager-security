package code.wolf.usermanagersecurity.infrastructure.out.persistence;

import code.wolf.usermanagersecurity.domain.model.User;
import code.wolf.usermanagersecurity.infrastructure.config.security.jwt.JwtService;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.entity.UserEntity;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class LoginPersistenceAdapterTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private LoginPersistenceAdapter loginPersistenceAdapter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoginSuccess() {

        User inputUser = User.builder()
                .email("ricardo@riguez.org")
                .password("hunter2A*")
                .build();

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("ricardo@riguez.org");
        userEntity.setPassword("hunter2A*");


        String expectedToken = "eyJhbGciOiJIUzI1NiJ9";
        User expectedUser = User.builder().token(expectedToken).build();

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(userEntity));
        when(jwtService.getToken(any(UserDetails.class))).thenReturn(expectedToken);

        User resultUser = loginPersistenceAdapter.login(inputUser);

        assertEquals(expectedUser.getToken(), resultUser.getToken());
    }

}