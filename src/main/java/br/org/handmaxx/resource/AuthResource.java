package br.org.handmaxx.resource;

import br.org.handmaxx.dto.usuario.UsuarioLoginDTO;
import br.org.handmaxx.dto.usuario.UsuarioResponseDTO;
import br.org.handmaxx.service.usuario.UsuarioService;
import br.org.handmaxx.service.usuario.hash.HashService;
import br.org.handmaxx.service.usuario.jwt.JwtService;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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

    @PermitAll
    @POST
    @Path("login")
    public Response login(@Valid UsuarioLoginDTO user){
        String hashSenha = hashService.getHashSenha(user.senha());
    
        UsuarioResponseDTO result = userService.findByLoginAndSenha(user.login(), hashSenha);
    
        String token = jwtService.generateJwt(result);
    
        return Response.ok(result)
                       .header("authorization", token)
                       .build();
    }
}
