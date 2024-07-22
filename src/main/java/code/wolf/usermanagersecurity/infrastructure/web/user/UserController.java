package code.wolf.usermanagersecurity.infrastructure.web.user;

import code.wolf.usermanagersecurity.aplication.port.in.UserPort;
import code.wolf.usermanagersecurity.common.WebAdapter;
import code.wolf.usermanagersecurity.domain.model.User;
import code.wolf.usermanagersecurity.infrastructure.dto.request.UserRequestDTO;
import code.wolf.usermanagersecurity.infrastructure.dto.response.ResponseDTO;
import code.wolf.usermanagersecurity.infrastructure.dto.response.UserResponseDTO;
import code.wolf.usermanagersecurity.infrastructure.persistence.Build;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import code.wolf.usermanagersecurity.infrastructure.persistence.transformers.UserTransformer;
import code.wolf.usermanagersecurity.infrastructure.persistence.transformers.UserMapper;


@WebAdapter
@RestController
@RequestMapping("/register/")
public class UserController {

    private final UserPort userPort;

    public UserController(UserPort userPort) {
        this.userPort = userPort;
    }


    @PostMapping(value = "save")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> register(@RequestBody UserRequestDTO userRequestDTO) {
        var userResponseDTO = userPort.save(UserTransformer.toToUser(userRequestDTO));
        return Build.build200Response(UserMapper.mapUserToDTO(userResponseDTO));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> updateRegister(
            @RequestBody UserRequestDTO userRequestDTO, @PathVariable String id) {

        var userResponseDTO = userPort.update(UserTransformer.toToUser(userRequestDTO), id);
        return Build.build200Response(UserMapper.mapUserToDTO(userResponseDTO));
    }
}
