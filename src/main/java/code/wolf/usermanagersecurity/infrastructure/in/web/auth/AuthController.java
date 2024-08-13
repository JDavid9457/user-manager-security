    package code.wolf.usermanagersecurity.infrastructure.in.web.auth;


    import code.wolf.usermanagersecurity.aplication.port.in.LoginPort;
    import code.wolf.usermanagersecurity.common.WebAdapter;
    import code.wolf.usermanagersecurity.domain.model.User;
    import code.wolf.usermanagersecurity.infrastructure.dto.request.AuthResponseDTO;
    import lombok.SneakyThrows;
    import org.springframework.http.ResponseEntity;
    import org.springframework.validation.annotation.Validated;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;


    @WebAdapter
    @RestController
    @RequestMapping("/auth")
    public class AuthController {

        private final LoginPort loginPort;

        public AuthController(LoginPort loginPort) {
            this.loginPort = loginPort;
        }


        @PostMapping(value = "/login")
        @SneakyThrows
        public ResponseEntity<AuthResponseDTO> login(@Validated @RequestBody User user) {
            User userResponse = loginPort.loginUser(user);
            String token = userResponse.getToken();
            AuthResponseDTO authResponseDTO = new AuthResponseDTO(token);
            return ResponseEntity.ok().body(authResponseDTO);
        }

}
