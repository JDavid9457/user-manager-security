package code.wolf.usermanagersecurity.infrastructure.in.web.user;

import code.wolf.usermanagersecurity.aplication.port.in.UserPort;
import code.wolf.usermanagersecurity.domain.model.Phone;
import code.wolf.usermanagersecurity.domain.model.User;
import code.wolf.usermanagersecurity.infrastructure.dto.PhoneDTO;
import code.wolf.usermanagersecurity.infrastructure.dto.request.UserDetailRequestDTO;
import code.wolf.usermanagersecurity.infrastructure.dto.request.UserRequestDTO;
import code.wolf.usermanagersecurity.infrastructure.dto.response.ResponseDTO;
import code.wolf.usermanagersecurity.infrastructure.dto.response.UserResponseDTO;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.entity.Role;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.transformers.UserEntityModelMapper;
import code.wolf.usermanagersecurity.infrastructure.util.auxiliary.ResponseEntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {


    @Mock
    private UserPort userPort;

    @Mock
    private UserEntityModelMapper userEntityModelMapper;

    @InjectMocks
    private UserController userController;


    private User user;
    private UserRequestDTO userRequestDTO;
    private UserResponseDTO userResponseDTO;
    private LocalDateTime date;
    private UserDetailRequestDTO userDetailRequestDTO;
    private PhoneDTO phoneDTO;
    private List<PhoneDTO> listPhoneDTO;
    private List<User> userList;
    private List<UserDetailRequestDTO> userDetailRequestDTOList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        date = LocalDateTime.now();
        user = User.builder()
                .id("1a950b23-d098-4ea0-83f3-2563ad4bfab8")
                .name("name")
                .email("ricardo@riguez.org")
                .password("hunter2A*")
                .phones(Collections.singletonList(
                        Phone.builder()
                                .number("12345")
                                .cityCode("1")
                                .contryCode("57")
                                .build()
                ))
                .createdAt(date)
                .modifiedAt(date)
                .lastLogin(date)
                .isActive(true)
                .role(Role.USER)
                .token("eyJhbGciOiJIUzI1NiJ9")
                .build();

        userRequestDTO = UserRequestDTO.builder()
                .username("name")
                .email("ricardo@riguez.org")
                .password("hunter2A*")
                .build();

        userResponseDTO = UserResponseDTO.builder()
                .id("1a950b23-d098-4ea0-83f3-2563ad4bfab8")
                .createdAt(date)
                .modifiedAt(date)
                .lastLogin(date)
                .token("eyJhbGciOiJIUzI1NiJ9")
                .isactive(true)
                .build();

        phoneDTO = PhoneDTO.builder()
                .number("12345")
                .cityCode("1")
                .contryCode("52")
                .build();

        listPhoneDTO = Collections.singletonList(phoneDTO);

        userDetailRequestDTO = UserDetailRequestDTO.builder()
                .id("1a950b23-d098-4ea0-83f3-2563ad4bfab8")
                .username("name")
                .email("ricardo@riguez.org")
                .phones(listPhoneDTO)
                .createdAt(date)
                .modifiedAt(date)
                .lastLogin(date)
                .isActive(true)
                .build();

        userList = Collections.singletonList(user);
        userDetailRequestDTOList = Collections.singletonList(userDetailRequestDTO);
    }

    @Test
    public void testRegister() throws Exception {
        var expectedResponse = ResponseEntityBuilder.buildSaveResponse(
                UserResponseDTO.builder()
                        .id("1a950b23-d098-4ea0-83f3-2563ad4bfab8")
                        .createdAt(date)
                        .modifiedAt(date)
                        .lastLogin(date)
                        .isactive(true)
                        .token("eyJhbGciOiJIUzI1NiJ9")
                        .build());

        var request = UserRequestDTO.builder()
                .email("ricardo@riguez.org")
                .password("hunter2A*")
                .phones(List.of())
                .build();

        when(userPort.save(any(User.class))).thenReturn(user);

        var result = userController.register(request);

        assertNotNull(result);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        ResponseDTO<UserResponseDTO> expectedBody = expectedResponse.getBody();
        ResponseDTO<UserResponseDTO> actualBody = result.getBody();
        assertNotNull(actualBody);
        assertNotNull(expectedBody);
        assertEquals(expectedBody.getData(), actualBody.getData());

    }

    @Test
    public void testListUser() {
        Mockito.when(userPort.findAll()).thenReturn(List.of(user));

        var result = userController.ListUser();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCode(), HttpStatusCode.valueOf(200));
    }

    @Test
    public void TestSearchIdUser() {
        when(userPort.findById(user.getId())).thenReturn(user);

        var result = userController.searchId(user.getId());

        assertNotNull(result);
        verify(userPort, times(1)).findById(user.getId());
        assertEquals(result.getStatusCode(), HttpStatusCode.valueOf(200));
    }

    @Test
    public void TestDeleteUser() {
        when(userPort.delete(user.getId())).thenReturn(true);

        var result = userController.delete(user.getId());

        assertNotNull(result);
        assertEquals(result.getStatusCode(), HttpStatusCode.valueOf(200));
    }


}