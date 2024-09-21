package br.org.handmaxx.service.usuario;

import br.org.handmaxx.model.Usuario;
import br.org.handmaxx.dto.usuario.UsuarioResponseDTO;
import br.org.handmaxx.repository.UsuarioRepository;
import jakarta.inject.Inject;

public class UsuarioServiceImpl implements UsuarioService {
    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    public UsuarioResponseDTO findByLoginAndSenha(String login, String senha) {
        Usuario usuario = usuarioRepository.findByLoginAndSenha(login, senha);
        return UsuarioResponseDTO.valueOf(usuario);
    }

    
}
