package net.learning.app.authapi.controller;

import lombok.RequiredArgsConstructor;
import net.learning.app.authapi.entity.dtos.AuthRequest;
import net.learning.app.authapi.entity.dtos.AuthResponse;
import net.learning.app.authapi.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody AuthRequest request) {
        authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build(); // 201
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request)); // 200 + { token }
    }
}
