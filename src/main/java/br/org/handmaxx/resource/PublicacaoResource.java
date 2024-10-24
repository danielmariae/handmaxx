package br.org.handmaxx.resource;

import java.io.IOException;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.org.handmaxx.app.result.Result;
import br.org.handmaxx.dto.publicacao.PublicacaoDTO;
import br.org.handmaxx.form.PublicacaoImageForm;
import br.org.handmaxx.service.publicacao.PublicacaoFileService;
import br.org.handmaxx.service.publicacao.PublicacaoService;
import br.org.handmaxx.util.Error;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

@Path("homepage")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PublicacaoResource {
    
    @Inject
    PublicacaoService publicacaoService;

    @Inject
    PublicacaoFileService publicacaoFileService;

    @POST
    @Transactional
    public Response create(@Valid PublicacaoDTO dto) {
        return Response.status(201).entity(publicacaoService.create(dto)).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(PublicacaoDTO dto, @PathParam("id") Long id) {

        try {
            PublicacaoDTO publicacao = publicacaoService.update(id, dto);
            return Response.ok(publicacao).build();

        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }

    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        publicacaoService.delete(id);
        return Response.noContent().build();
    }

    @GET
    //@RolesAllowed("User")
    public Response findAll(
                @QueryParam("page") @DefaultValue("0") int page,
                @QueryParam("pageSize") @DefaultValue("100") int pageSize) {

        return Response.ok(publicacaoService.getAll(page, pageSize)).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        return Response.status(200).entity(publicacaoService.findById(id)).build();
    }

    @PATCH
    @Path("/upload/imagem/{id}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(@MultipartForm PublicacaoImageForm form, @PathParam("id") Long id) {
        String nomeImagem;
        try {
            nomeImagem = publicacaoFileService.salvar(form.getNomeImagem(), form.getImagem());
        } catch (IOException e) {
            e.printStackTrace();
            Error error = new Error("409", e.getMessage());
            return Response.status(Status.CONFLICT).entity(error).build();
        }

        publicacaoService.updateNomeImagem(id, nomeImagem);

        return Response.ok(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/download/imagem/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        ResponseBuilder response = Response.ok(publicacaoFileService.obter(nomeImagem));
        response.header("Content-Disposition", "attachment;filename=" + nomeImagem);
        return response.build();
    }
}
