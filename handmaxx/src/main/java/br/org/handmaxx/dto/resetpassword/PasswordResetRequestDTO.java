package br.org.handmaxx.dto.resetpassword;

import jakarta.validation.constraints.NotBlank;

public record PasswordResetRequestDTO(
    @NotBlank String email
) {}
