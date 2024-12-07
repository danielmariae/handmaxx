package br.org.handmaxx.dto.treino;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.org.handmaxx.dto.atleta.AtletaTreinoDTO;
import br.org.handmaxx.model.Treino;

public record TreinoFullResponseDTO(
    Long id,    
    String local,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    LocalDateTime dataHorario,
    List<AtletaTreinoDTO> listarAtletas
) {
    public static TreinoFullResponseDTO valueOf(Treino t){
        return new TreinoFullResponseDTO(t.getId(),
                                         t.getLocal(),
                                         t.getDataHorario(),
                                         t.getListaAtletas().stream().map(AtletaTreinoDTO::valueOf).collect(Collectors.toList()));
    }
}
