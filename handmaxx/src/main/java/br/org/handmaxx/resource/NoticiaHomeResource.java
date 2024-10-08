package br.org.handmaxx.resource;

import br.org.handmaxx.dto.noticiaHome.NoticiaHomeDTO;
import br.org.handmaxx.service.noticiaHome.NoticiaHomeService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("noticia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NoticiaHomeResource {
    
    @Inject
    NoticiaHomeService noticiaService;

    @POST
    @Transactional
    public Response create(@Valid NoticiaHomeDTO dto) {
        return Response.status(201).entity(noticiaService.create(dto)).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        return Response.status(200).entity(noticiaService.findById(id)).build();
    }
}
