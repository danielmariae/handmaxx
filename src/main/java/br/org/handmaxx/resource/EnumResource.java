package br.org.handmaxx.resource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.org.handmaxx.dto.enums.CategoriaDTO;
import br.org.handmaxx.dto.enums.NotificacaoAntesDTO;
import br.org.handmaxx.model.Categoria;
import br.org.handmaxx.model.NotificacaoAntes;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/enum")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnumResource {
    @GET
    @Path("/categorias")
    public List<CategoriaDTO> obterStatusPedido() {
        return Arrays.asList(Categoria.values())
        .stream()
        .map(categoria -> CategoriaDTO.valueOf(categoria))
        .collect(Collectors.toList());
    }

    @GET
    @Path("/frequencia-notificacao")
    public List<NotificacaoAntesDTO> obterFrequenciasNotificacao() {
        return Arrays.asList(NotificacaoAntes.values())
        .stream()
        .map(n -> NotificacaoAntesDTO.valueOf(n))
        .collect(Collectors.toList());
    }
}
