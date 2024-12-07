package br.org.handmaxx.dto.treino;

import java.time.LocalDateTime;
import java.util.List;

import br.org.handmaxx.dto.atleta.AtletaTreinoDTO;
import br.org.handmaxx.dto.enums.NotificacaoAntesDTO;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TreinoDTO(
    // Atualizar esse treino depois com a UML.
    @NotBlank
    String local,
    @NotNull @FutureOrPresent(message = "Proibido marcar com datas no passado.")
    LocalDateTime dataHorario,
    NotificacaoAntesDTO notificarEm,
    List<AtletaTreinoDTO> listarAtletas
    ) {}
