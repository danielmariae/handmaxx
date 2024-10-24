package br.org.handmaxx.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.org.handmaxx.dto.treino.TreinoDTO;
import br.org.handmaxx.service.treino.TreinoService;
// import io.quarkus.security.Authenticated;
import io.quarkus.security.Authenticated;
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

@Authenticated
@Path("treino")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TreinoResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    TreinoService treinoService;

    @POST
    @Transactional
    public Response create(@Valid TreinoDTO dto) {
        return Response.status(201).entity(treinoService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(TreinoDTO dto, @PathParam("id") Long id) {

        treinoService.update(dto, id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {

        treinoService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    // Permitir que qualquer usuário autenticado possa buscar um atleta por ID
    @GET
    @Path("/{id}")
    @Transactional

    public Response getById(@PathParam("id") Long id) {

        return Response.status(200).entity(treinoService.findById(id)).build();
    }

//     // Permitir que qualquer usuário autenticado possa buscar atletas por nome
//     @GET
//     @Path("/nome/{nome}")
//     @Transactional

//     public Response getByNome(@PathParam("nome") String nome) {
//         List<AtletaResponseDTO> atletas = atletaService.findByNome(nome);
//         return Response.ok(atletas).build();
//     }
// 
}
