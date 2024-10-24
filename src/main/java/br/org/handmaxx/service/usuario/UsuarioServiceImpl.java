package br.org.handmaxx.service.usuario;

import br.org.handmaxx.app.error.custom.CustomException;
import br.org.handmaxx.app.error.global.ErrorResponse;
import br.org.handmaxx.dto.usuario.UsuarioDTO;
import br.org.handmaxx.dto.usuario.UsuarioResponseDTO;
import br.org.handmaxx.model.Usuario;
import br.org.handmaxx.repository.UsuarioRepository;
import br.org.handmaxx.service.usuario.hash.HashService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;


@ApplicationScoped
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
    public UsuarioResponseDTO findById(Long id){
        Usuario user = usuarioRepository.findById(id);
        if(user == null){
            throw new CustomException(new ErrorResponse("Usuário não encontrado", "UsuarioServiceImpl(update)", 404));
        }
        return UsuarioResponseDTO.valueOf(user);
    }

    @Override
    public UsuarioResponseDTO create(UsuarioDTO dto){
        Usuario user = new Usuario();

        user.setEmail(dto.email());
        user.setLogin(dto.login());
        user.setSenha(hashService.getHashSenha(dto.senha()));
        
        try {
            usuarioRepository.persist(user);
        } catch (PersistenceException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                "Erro ao criar usuário", 
                "UsuarioServiceImpl(insert): " + e.getMessage(),
                500);
                
            throw new CustomException(errorResponse);
        }
        return UsuarioResponseDTO.valueOf(user);
    }

    @Override
    public UsuarioResponseDTO update(UsuarioDTO dto, Long id){
        Usuario user = usuarioRepository.findById(id);
        if(user == null){
            throw new CustomException(new ErrorResponse("Usuário não encontrado", "UsuarioServiceImpl(update)", 404));
        }
        user.setEmail(dto.email());
        user.setLogin(dto.login());
        if(!dto.senha().isEmpty()){
            user.setSenha(hashService.getHashSenha(dto.senha()));
        }

        return UsuarioResponseDTO.valueOf(user);
    }

    @Override
    public void delete(Long id){
        Usuario user = usuarioRepository.findById(id);
        if(user == null){
            throw new CustomException(new ErrorResponse("Usuário não encontrado", "UsuarioServiceImpl(delete)", 404));
        }
        try {
            usuarioRepository.delete(user);
        } catch (Exception e) {
            throw new CustomException(new ErrorResponse("Erro no servidor.", "UsuarioServiceImpl(delete)", 500));
        }
    }
}
