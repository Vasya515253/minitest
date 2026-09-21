package net.learning.app.authapi.entity.dtos;

import java.util.UUID;

public record AuthResponse(
        String token,
        UUID userId
) {}