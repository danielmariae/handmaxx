package br.org.handmaxx.dto.resetpassword;

public record PasswordResetDTO(
    String token, 
    String newPassword,
    String confirmPassword)
    {}
