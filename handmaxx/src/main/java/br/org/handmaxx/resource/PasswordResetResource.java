package br.org.handmaxx.resource;

import br.org.handmaxx.dto.resetpassword.PasswordResetDTO;
import br.org.handmaxx.dto.resetpassword.PasswordResetRequestDTO;
import br.org.handmaxx.dto.resetpassword.PasswordResetResponseDTO;
import br.org.handmaxx.service.reset_password.PasswordResetService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/forgot-password")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PasswordResetResource {
    
    @Inject
    PasswordResetService passwordResetService;

    @POST
    @Path("/request")
    public Response requestPasswordReset(PasswordResetRequestDTO request){
        passwordResetService.requestPasswordReset(request);
        return Response.ok(new PasswordResetResponseDTO("Email de recuperação enviado, se o email for válido.")).build();
    }

    @GET
    @Path("/validate")
    public Response validateToken(@QueryParam("token") String token){
        boolean isValid = passwordResetService.validateResetToken(token);
        return Response.ok(isValid).build();
    }

    @POST
    @Path("/reset")
    public Response resetPassword(PasswordResetDTO resetDTO){
        passwordResetService.resetPassword(resetDTO);
        return Response.ok(new PasswordResetResponseDTO("Senha redefinida com sucesso.")).build();
    }
}
