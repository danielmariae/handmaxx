package br.org.handmaxx.resource;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.org.handmaxx.dto.atleta.AtletaCadastroInicialDTO;
import br.org.handmaxx.dto.atleta.AtletaDTO;
import br.org.handmaxx.dto.atleta.AtletaResponseDTO;
import br.org.handmaxx.dto.atleta.AtletaTreinoDTO;
import br.org.handmaxx.dto.resetpassword.PasswordResetResponseDTO;
import br.org.handmaxx.service.atleta.AtletaService;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("atleta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AtletaResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    AtletaService atletaService;

    // Cadastro inicial com PATCH (dados mínimos)
    @POST
    @Path("/initial")
    @Transactional
    @Authenticated
    public Response createInitial(@Valid AtletaCadastroInicialDTO dto) {
        return Response.status(201).entity(atletaService.createInitial(dto)).build();
    }

    // Cadastro inicial com PATCH (dados mínimos)
    @POST
    @Path("/initial/{treino}")
    @Transactional
    @Authenticated
    public Response createInitialWithTreino(@Valid AtletaCadastroInicialDTO dto, @PathParam("treino") Long treinoId) {
        return Response.status(201).entity(atletaService.createInitialWithTreino(dto, treinoId)).build();
    }
    @PUT
    @Path("/token/{token}")
    @PermitAll
    @Transactional
    public Response completarCadastro(@PathParam("token") String token, @Valid AtletaDTO dto){
        atletaService.completarCadastroToken(dto, token);
        return Response.status(Status.NO_CONTENT).build();
    }

    @POST
    @Authenticated
    @Transactional
    public Response create(@Valid AtletaDTO dto) {

        return Response.status(201).entity(atletaService.create(dto)).build();
    }

    @PUT
    @Path("/update/{id}")
    @Authenticated
    @Transactional
    public Response update(@Valid AtletaDTO dto, @PathParam("id") Long id) {

        atletaService.update(dto, id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    @Authenticated
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        atletaService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    // Permitir que qualquer usuário autenticado possa buscar um atleta por ID
    @GET
    @Path("/{id}")
    @Authenticated
    @Transactional
    public Response getById(@PathParam("id") Long id) {
        return Response.status(200).entity(atletaService.findById(id)).build();
    }

    // Permitir que qualquer usuário autenticado possa buscar atletas por nome
    @GET
    @Path("/nome/{nome}")
    @Authenticated
    @Transactional
    public Response getByNome(@PathParam("nome") String nome) {
        List<AtletaResponseDTO> atletas = atletaService.findByNome(nome);
        return Response.ok(atletas).build();
    }

    @GET
    @Path("/all")
    @Authenticated
    @Transactional
    public Response findAll() {
        List<AtletaResponseDTO> atletas = atletaService.findAll();
        return Response.ok(atletas).build();
    }

    @GET
    @Path("/all/treinos")
    @Authenticated
    @Transactional
    public Response findAllTreinos() {
        List<AtletaTreinoDTO> atletas = atletaService.findAllTreinos();
        return Response.ok(atletas).build();
    }

    @POST
    @Authenticated
    @Path("/{id}/gerar-token")
    public Response gerarTokenCadastro(@PathParam("id") Long atletaId) {
        try {
            atletaService.gerarTokenCadastro(atletaId);
            return Response.ok(new PasswordResetResponseDTO("Token gerado e enviado pelo WhatsApp com sucesso!")).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @PermitAll
    @Path("/validar-token/{token}")
    public Response validarToken(@PathParam("token") String token) {
        try {
            return Response.ok(atletaService.validarToken(token)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/count/all")
    @Authenticated
    @Transactional
    public Response countTodosAtletas() {
        return Response.ok(atletaService.countTodosAtletas()).build();
    }

}
