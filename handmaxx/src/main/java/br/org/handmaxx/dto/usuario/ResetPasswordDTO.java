package br.org.handmaxx.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record ResetPasswordDTO(
    @NotBlank String token,
    @NotBlank String novaSenha
) {}
