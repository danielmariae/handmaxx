package br.org.handmaxx.service.usuario;

import br.org.handmaxx.dto.usuario.UsuarioResponseDTO;

public interface UsuarioService {
    public UsuarioResponseDTO findByLoginAndSenha(String login, String senha);
}
