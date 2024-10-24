package br.org.handmaxx.service.usuario.jwt;

import br.org.handmaxx.dto.usuario.UsuarioResponseDTO;

public interface JwtService {
    public String generateJwt(UsuarioResponseDTO dto);
}
