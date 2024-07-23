package code.wolf.usermanagersecurity.infrastructure.in.web.auth;

import code.wolf.usermanagersecurity.aplication.port.in.LoginPort;
import code.wolf.usermanagersecurity.domain.model.Phone;
import code.wolf.usermanagersecurity.domain.model.User;
import code.wolf.usermanagersecurity.infrastructure.dto.request.AuthResponseDTO;
import code.wolf.usermanagersecurity.infrastructure.dto.request.LoginRequestDTO;
import code.wolf.usermanagersecurity.infrastructure.dto.response.UserResponseDTO;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.entity.Role;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {


    @Mock
    private LoginPort loginPort;

    @InjectMocks
    private AuthController authController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
        objectMapper = new ObjectMapper();
    }


    @Test
    public void testLoginSuccess() throws Exception {
//        LoginRequestDTO request = new LoginRequestDTO();
//        request.setEmail("david@email.com");
//        request.setPassword("david945L");
//
//        User user = User.builder()
//                .email("david@email.com")
//                .password("david945L")
//                .token("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyaWNhcmRvIFJvZHJpZ3VleiIsImlhdCI6MTcyMTc1MjczMCwiZXhwIjoxNzIxNzU0MTcwfQ.wLRUJa3i3fNy8Y2QAbpHP3gEQJOMXbmR-uHxuIDnv88")
//                .build();
//        AuthResponseDTO response = AuthResponseDTO.builder()
//                .token("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyaWNhcmRvIFJvZHJpZ3VleiIsImlhdCI6MTcyMTc1MjczMCwiZXhwIjoxNzIxNzU0MTcwfQ.wLRUJa3i3fNy8Y2QAbpHP3gEQJOMXbmR-uHxuIDnv88")
//                .build();
//
//        when(loginPort.loginUser(any(User.class))).thenReturn(user);
//
//        mockMvc.perform(post("/auth/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }
}


