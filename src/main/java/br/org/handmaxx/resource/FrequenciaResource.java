package br.org.handmaxx.resource;

import java.util.List;

import br.org.handmaxx.dto.frequencia.FrequenciaDTO;
import br.org.handmaxx.service.frequencia.FrequenciaService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/frequencia")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FrequenciaResource {

    @Inject
    FrequenciaService frequenciaService;

    @GET
    @Path("/treino/{treinoId}")
    public Response listarFrequenciasPorTreino(@PathParam("treinoId") Long treinoId) {
        return Response.status(201).entity(frequenciaService.listarFrequenciasPorTreino(treinoId)).build();
    }

    @POST
    @Path("/registrar")
    public Response registrarFrequencia(FrequenciaDTO frequenciaDTO) {
        frequenciaService.registrarPresenca(frequenciaDTO);
        return Response.ok().build();
    }

    @POST
    @Path("/registrar-multiplas")
    public Response registrarFrequencia(List<FrequenciaDTO> frequenciasDTO) {
        frequenciaService.registrarVariasFrequencias(frequenciasDTO);
        return Response.ok().build();
    }

    @GET
    @Path("/treino/{treinoId}/atletas")
    public Response listarAtletasPorTreino(@PathParam("treinoId") Long treinoId) {
        return Response.status(201).entity(frequenciaService.listarAtletasPorTreino(treinoId)).build();
    }
}
