package code.wolf.usermanagersecurity.infrastructure.persistence;

import code.wolf.usermanagersecurity.aplication.port.in.LoginPort;
import code.wolf.usermanagersecurity.common.PersistenceAdapter;
import code.wolf.usermanagersecurity.domain.model.User;
import code.wolf.usermanagersecurity.infrastructure.config.security.jwt.JwtService;
import code.wolf.usermanagersecurity.infrastructure.persistence.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
@PersistenceAdapter
public class LoginPersistenceAdapter implements LoginPort {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginPersistenceAdapter(UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public User loginUser(User user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        UserDetails userDetails = userRepository.findByEmail(user.getEmail()).orElseThrow();
        String token = jwtService.getToken(userDetails);
        return User.builder().token(token).build();
    }
}
