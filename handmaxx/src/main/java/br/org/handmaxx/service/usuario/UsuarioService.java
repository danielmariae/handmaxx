package br.org.handmaxx.service.usuario;

import br.org.handmaxx.dto.usuario.UsuarioDTO;
import br.org.handmaxx.dto.usuario.UsuarioResponseDTO;

public interface UsuarioService {
    public UsuarioResponseDTO findByLoginAndSenha(String login, String senha);
    public UsuarioResponseDTO create(UsuarioDTO dto);
}
