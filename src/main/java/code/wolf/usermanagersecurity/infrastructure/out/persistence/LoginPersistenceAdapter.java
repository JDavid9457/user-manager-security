package code.wolf.usermanagersecurity.infrastructure.out.persistence;

import code.wolf.usermanagersecurity.aplication.port.out.LoginUserPort;
import code.wolf.usermanagersecurity.common.PersistenceAdapter;
import code.wolf.usermanagersecurity.domain.model.User;
import code.wolf.usermanagersecurity.infrastructure.config.security.jwt.JwtService;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
@PersistenceAdapter
public class LoginPersistenceAdapter implements LoginUserPort {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginPersistenceAdapter(UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public User login(User user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        UserDetails userDetails = userRepository.findByEmail(user.getEmail()).orElseThrow();
        String token = jwtService.getToken(userDetails);
        return User.builder().token(token).build();
    }

}
