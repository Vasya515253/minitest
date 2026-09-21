package net.learning.app.authapi.service;


import lombok.RequiredArgsConstructor;
import net.learning.app.authapi.entity.dtos.AuthRequest;
import net.learning.app.authapi.entity.dtos.AuthResponse;
import net.learning.app.authapi.entity.User;
import net.learning.app.authapi.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public void register(AuthRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalStateException("Email вже зайнятий");
        }

        User user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();

        userRepository.save(user);
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalStateException("User not found"));

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token, user.getId());

    }

}
