package br.org.handmaxx.dto.treino;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import br.org.handmaxx.dto.atleta.AtletaTreinoDTO;
import br.org.handmaxx.model.Treino;

public record TreinoFullResponseDTO(
    Long id,    
    String local,
    LocalDate data,
    LocalTime horario,
    List<AtletaTreinoDTO> listarAtletas
) {
    public static TreinoFullResponseDTO valueOf(Treino t){
        return new TreinoFullResponseDTO(t.getId(),
                                         t.getLocal(),
                                         t.getData(),
                                         t.getHorario(),
                                         t.getListaAtletas().stream().map(AtletaTreinoDTO::valueOf).collect(Collectors.toList()));
    }
}
