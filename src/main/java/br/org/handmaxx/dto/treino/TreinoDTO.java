package br.org.handmaxx.dto.treino;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.org.handmaxx.dto.atleta.AtletaTreinoDTO;
import br.org.handmaxx.model.Treino;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TreinoDTO(
    // Atualizar esse treino depois com a UML.
    @NotBlank
    String local,
    @NotNull @FutureOrPresent(message = "Proibido marcar com datas no passado.")
    LocalDateTime dataHorario,
    List<AtletaTreinoDTO> listarAtletas
    ) {
    public static TreinoDTO valueOf(Treino t){
        return new TreinoDTO(
            t.getLocal(),
            t.getDataHorario(),
            t.getListaAtletas().stream().map(AtletaTreinoDTO::valueOf).collect(Collectors.toList())
        );
    }
}
