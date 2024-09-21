package br.org.handmaxx.dto.usuario;

import br.org.handmaxx.model.Usuario;

public record UsuarioDTO (
    String cnpj,
    String login,
    String senha
) {
    public static UsuarioDTO valueOf(Usuario usuario){
        return new UsuarioDTO(
            usuario.getCnpj(),
            usuario.getLogin(),
            usuario.getSenha()
        );
    }
}
