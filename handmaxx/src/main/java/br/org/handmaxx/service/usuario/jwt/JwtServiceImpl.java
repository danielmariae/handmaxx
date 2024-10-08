package br.org.handmaxx.service.usuario.jwt;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import br.org.handmaxx.dto.usuario.UsuarioResponseDTO;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JwtServiceImpl implements JwtService {
    private static final Duration EXPIRATION_TIME = Duration.ofHours(24);

    @Override
    public String generateJwt(UsuarioResponseDTO dto){
        Instant now = Instant.now();
            Instant expiryDate = now.plus(EXPIRATION_TIME);
    
            Set<String> roles = new HashSet<String>();
            roles.add("User");
    
            return Jwt.issuer("handmaxx-jwt")
            .subject(dto.login())
            .groups(roles)
            .expiresAt(expiryDate)
            .sign();
        }    
}
