package br.org.handmaxx.dto.usuario;

import br.org.handmaxx.model.Usuario;

public record UsuarioLoginDTO (
    String login,
    String senha
) {
    public static UsuarioLoginDTO valueOf(Usuario usuario){
        return new UsuarioLoginDTO(
            usuario.getLogin(),
            usuario.getSenha()
        );
    }
}
