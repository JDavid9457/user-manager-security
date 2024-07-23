package code.wolf.usermanagersecurity.infrastructure.in.web.user;

import code.wolf.usermanagersecurity.aplication.port.in.UserPort;
import code.wolf.usermanagersecurity.domain.model.User;
import code.wolf.usermanagersecurity.infrastructure.dto.request.UserRequestDTO;
import code.wolf.usermanagersecurity.infrastructure.dto.response.UserResponseDTO;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.entity.Role;
import code.wolf.usermanagersecurity.infrastructure.util.auxiliary.ResponseEntityBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserPort userPort;

    @InjectMocks
    private UserController userController;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;
    private UserRequestDTO userRequestDTO;
    private UserResponseDTO userResponseDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        user = User.builder()
                .id("1a950b23-d098-4ea0-83f3-2563ad4bfab8")
                .name("name")
                .email("email")
                .password("password")
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .isActive(true)
                .role(Role.USER)
                .build();

        userRequestDTO = UserRequestDTO.builder()
                .username("name")
                .email("email")
                .password("password")
                .build();

        userResponseDTO = UserResponseDTO.builder()
                .id("1a950b23-d098-4ea0-83f3-2563ad4bfab8")
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .token("eyJhbGciOiJIUzI1NiJ9")
                .isactive(true)
                .build();
    }

    @Test
   public void testRegister() throws Exception {
        Mockito.when(userPort.save(any(User.class))).thenReturn(User.builder().email("example@example.com").phones(List.of()).build());
    }

}