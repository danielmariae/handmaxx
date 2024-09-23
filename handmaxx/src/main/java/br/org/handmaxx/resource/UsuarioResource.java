package br.org.handmaxx.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.org.handmaxx.dto.usuario.UsuarioDTO;
import br.org.handmaxx.service.usuario.UsuarioService;
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

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    UsuarioService userService;

    @POST
    @Transactional
    public Response create(@Valid UsuarioDTO dto){
        return Response.status(201).entity(userService.create(dto)).build();
    }
    
    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@Valid UsuarioDTO dto, @PathParam("id") Long id){
        userService.update(dto, id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        userService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Transactional
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id){        
        return Response.status(200).entity(userService.findById(id)).build();
    }
}
