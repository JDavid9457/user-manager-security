package code.wolf.usermanagersecurity.infrastructure.web.auth;

import code.wolf.usermanagersecurity.aplication.impl.LoginServiceImp;
import code.wolf.usermanagersecurity.aplication.port.in.LoginPort;
import code.wolf.usermanagersecurity.common.WebAdapter;
import code.wolf.usermanagersecurity.domain.model.User;
import code.wolf.usermanagersecurity.infrastructure.dto.UserDTO;
import code.wolf.usermanagersecurity.infrastructure.dto.request.LoginRequestDTO;
import code.wolf.usermanagersecurity.infrastructure.dto.response.TokenResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@WebAdapter
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final LoginPort loginPort;

    public AuthController(LoginPort loginPort) {
        this.loginPort = loginPort;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<TokenResponseDTO> login(@Validated @RequestBody User user) {
       var userResponseDTO = loginPort.loginUser(user);
        TokenResponseDTO tokenResponseDTO = TokenResponseDTO
                .builder()
                .data(userResponseDTO)
                .status(HttpStatus.OK.value())
                .message("Ingreso")
                .build();

        return ResponseEntity.ok().body(tokenResponseDTO);
    }


}
