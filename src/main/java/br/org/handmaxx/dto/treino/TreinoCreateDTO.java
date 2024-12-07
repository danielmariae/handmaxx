package br.org.handmaxx.dto.treino;

import java.time.LocalDateTime;
import java.util.List;


import br.org.handmaxx.dto.atleta.AtletaTreinoDTO;
import br.org.handmaxx.dto.enums.CategoriaDTO;
import br.org.handmaxx.dto.enums.NotificacaoAntesDTO;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TreinoCreateDTO(
    @NotBlank
    String local,
    @NotNull @FutureOrPresent(message = "Proibido marcar com datas no passado.") 
    LocalDateTime dataHorario,
    Boolean criarTreinoTodosAtletas,
    Boolean notificarAtletasAgora,
    List<CategoriaDTO> listarCategorias,
    List<AtletaTreinoDTO> listarAtletas,
    NotificacaoAntesDTO notificarEm
) {}
