package br.org.handmaxx.dto.usuario;

import br.org.handmaxx.model.Usuario;

public record UsuarioResponseDTO(
    String email,
    String login
) {
    public static UsuarioResponseDTO valueOf(Usuario usuario){
        return new UsuarioResponseDTO(
            usuario.getEmail(),
            usuario.getLogin()
        );
    }
}
