package br.org.handmaxx.dto.treino;

import java.time.LocalDate;
import java.time.LocalTime;

import br.org.handmaxx.model.Treino;

public record TreinoDTO(
    // Atualizar esse treino depois com a UML.
    String local,
    LocalDate data,
    LocalTime horario
) {
    public static TreinoDTO valueOf(Treino t){
        return new TreinoDTO(
            t.getLocal(),
            t.getData(),
            t.getHorario()
        );
    }
}
