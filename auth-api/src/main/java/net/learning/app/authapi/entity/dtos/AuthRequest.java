package net.learning.app.authapi.entity.dtos;

public record AuthRequest(
        String email,
        String password
) {}