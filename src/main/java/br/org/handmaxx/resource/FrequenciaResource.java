package br.org.handmaxx.resource;

import java.util.List;

import br.org.handmaxx.dto.atleta.AtletaTreinoDTO;
import br.org.handmaxx.dto.frequencia.FrequenciaDTO;
import br.org.handmaxx.model.Frequencia;
import br.org.handmaxx.service.frequencia.FrequenciaService;
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
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FrequenciaResource {

    @Inject
    FrequenciaService frequenciaService;

    @GET
    @Path("/treino/{treinoId}")
    public List<Frequencia> listarFrequenciasPorTreino(@PathParam("treinoId") Long treinoId) {
        return frequenciaService.listarFrequenciasPorTreino(treinoId);
    }

    @POST
    @Path("/registrar")
    public Response registrarFrequencia(FrequenciaDTO frequenciaDTO) {
        frequenciaService.registrarPresenca(frequenciaDTO);
        return Response.ok().build();
    }

    @GET
    @Path("/treino/{treinoId}/atletas")
    public List<AtletaTreinoDTO> listarAtletasPorTreino(@PathParam("treinoId") Long treinoId) {
        return frequenciaService.listarAtletasPorTreino(treinoId);
    }
}
