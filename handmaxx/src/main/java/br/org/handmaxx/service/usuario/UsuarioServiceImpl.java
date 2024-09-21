package br.org.handmaxx.service.usuario;

import br.org.handmaxx.model.Usuario;
import br.org.handmaxx.dto.usuario.UsuarioDTO;
import br.org.handmaxx.dto.usuario.UsuarioResponseDTO;
import br.org.handmaxx.repository.UsuarioRepository;
import br.org.handmaxx.service.usuario.hash.HashService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

public class UsuarioServiceImpl implements UsuarioService {
    @Inject
    UsuarioRepository usuarioRepository;

    @Inject 
    HashService hashService;

    @Override
    public UsuarioResponseDTO findByLoginAndSenha(String login, String senha) {
        Usuario usuario = usuarioRepository.findByLoginAndSenha(login, senha);
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    public UsuarioResponseDTO create(@Valid UsuarioDTO dto){
        Usuario user = new Usuario();

        user.setCnpj(dto.cnpj());
        user.setLogin(dto.login());
        user.setSenha(hashService.getHashSenha(dto.senha()));
        
        return null;
    }

    
}
