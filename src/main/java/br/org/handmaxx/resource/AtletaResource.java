package br.org.handmaxx.resource;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.org.handmaxx.dto.atleta.AtletaCadastroInicialDTO;
import br.org.handmaxx.dto.atleta.AtletaDTO;
import br.org.handmaxx.dto.atleta.AtletaResponseDTO;
import br.org.handmaxx.service.atleta.AtletaService;
import io.quarkus.security.Authenticated;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Authenticated
@Path("atleta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AtletaResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    AtletaService atletaService;

    // Cadastro inicial com PATCH (dados mínimos)
    @PATCH
    @Transactional
    public Response createInitial(@Valid AtletaCadastroInicialDTO dto) {
        return Response.status(201).entity(atletaService.createInitial(dto)).build();
    }

    @POST
    @Transactional
    public Response create(@Valid AtletaDTO dto) {

        return Response.status(201).entity(atletaService.create(dto)).build();
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response update(@Valid AtletaDTO dto, @PathParam("id") Long id) {

        atletaService.update(dto, id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {

        atletaService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    // Permitir que qualquer usuário autenticado possa buscar um atleta por ID
    @GET
    @Path("/{id}")
    @Transactional

    public Response getById(@PathParam("id") Long id) {

        return Response.status(200).entity(atletaService.findById(id)).build();
    }

    // Permitir que qualquer usuário autenticado possa buscar atletas por nome
    @GET
    @Path("/nome/{nome}")
    @Transactional
    public Response getByNome(@PathParam("nome") String nome) {
        List<AtletaResponseDTO> atletas = atletaService.findByNome(nome);
        return Response.ok(atletas).build();
    }

    @GET
    @Path("/all")
    @Transactional
    public Response findAll() {
        List<AtletaResponseDTO> atletas = atletaService.findAll();
        return Response.ok(atletas).build();
    }
}
