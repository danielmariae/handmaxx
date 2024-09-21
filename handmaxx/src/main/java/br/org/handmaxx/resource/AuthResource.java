package br.org.handmaxx.resource;

import br.org.handmaxx.service.usuario.UsuarioService;
import br.org.handmaxx.service.usuario.hash.HashService;
import br.org.handmaxx.service.usuario.jwt.JwtService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {
    
    @Inject
    UsuarioService userService;

    @Inject
    HashService hashService;

    @Inject
    JwtService jwtService;    
}
