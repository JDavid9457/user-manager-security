package code.wolf.usermanagersecurity.infrastructure.in.web.user;

import code.wolf.usermanagersecurity.aplication.port.in.UserPort;
import code.wolf.usermanagersecurity.common.WebAdapter;
import code.wolf.usermanagersecurity.infrastructure.dto.request.UserDetailRequestDTO;
import code.wolf.usermanagersecurity.infrastructure.dto.request.UserRequestDTO;
import code.wolf.usermanagersecurity.infrastructure.dto.response.ResponseDTO;
import code.wolf.usermanagersecurity.infrastructure.dto.response.UserResponseDTO;
import code.wolf.usermanagersecurity.infrastructure.util.auxiliary.ResponseEntityBuilder;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.transformers.UserEntityModelMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.transformers.UserTransformer;

import java.util.List;


@WebAdapter
@RestController
@RequestMapping("/register/")
public class UserController {

    private final UserPort userPort;

    public UserController(UserPort userPort) {
        this.userPort = userPort;
    }

    @Operation(summary = "Get all users", description = "Retrieve a list of all users")
    @PostMapping(value = "save")
    @SneakyThrows
    public ResponseEntity<ResponseDTO<UserResponseDTO>> register(@RequestBody UserRequestDTO userRequestDTO) {
        var userResponseDTO = userPort.save(UserTransformer.toToUser(userRequestDTO));
        return ResponseEntityBuilder.buildSaveResponse(UserEntityModelMapper.mapUserToDTO(userResponseDTO));
    }

    @PutMapping("update/{id}")
    @SneakyThrows
    public ResponseEntity<ResponseDTO<UserResponseDTO>> updateRegister(
            @RequestBody UserRequestDTO userRequestDTO, @PathVariable String id) {
        var userResponseDTO = userPort.update(UserTransformer.toToUser(userRequestDTO), id);
        return ResponseEntityBuilder.buildSaveResponse(UserEntityModelMapper.mapUserToDTO(userResponseDTO));
    }

    @GetMapping("/list")
    @SneakyThrows
    public ResponseEntity<ResponseDTO<List<UserDetailRequestDTO>>> ListUser() {
        var listUsers = userPort.findAll();
        List<UserDetailRequestDTO> userDetailList = UserEntityModelMapper.userToDetailListDTO(listUsers);
        return ResponseEntityBuilder.buildListResponse(userDetailList);
    }

    @GetMapping("/user/{id}")
    @SneakyThrows
    public ResponseEntity<ResponseDTO<UserDetailRequestDTO>> searchId(@PathVariable String id) {
        var searchById = userPort.findById(id);
        UserDetailRequestDTO userDetail = UserEntityModelMapper.userToDetailDTO(searchById);
        return ResponseEntityBuilder.buildFindByIdResponse(userDetail);
    }

    @GetMapping("/delete/{id}")
    @SneakyThrows
    public ResponseEntity<ResponseDTO<Boolean>> delete(@PathVariable String id) {
        boolean isDeleted = userPort.delete(id);
        return ResponseEntityBuilder.buildDeletedResponse(isDeleted);
    }
}
