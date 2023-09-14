package ma.gemadec.securityv2.services;

import lombok.RequiredArgsConstructor;
import ma.gemadec.securityv2.entities.User;
import ma.gemadec.securityv2.enumeration.Role;
import ma.gemadec.securityv2.repositories.UserRepository;
import ma.gemadec.securityv2.utils.AuthenticationRequest;
import ma.gemadec.securityv2.utils.AuthenticationResponse;
import ma.gemadec.securityv2.utils.RegisterRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest req) {
        User user  = User.builder()
                .firstName(req.getFirstName())
                .lastName(req.getLastName())
                .email((req.getEmail()))
                .password(passwordEncoder.encode(req.getPassword()))
                .roles(Role.USER)
                .build();
        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(),
                        req.getPassword()
                )
        );
        User user = userRepository.findUserByEmail(req.getEmail()).orElseThrow();
        String jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }
}
