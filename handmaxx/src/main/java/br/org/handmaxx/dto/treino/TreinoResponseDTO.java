package br.org.handmaxx.dto.treino;

import java.time.LocalDate;
import java.time.LocalTime;

import br.org.handmaxx.model.Treino;

public record TreinoResponseDTO(
        // Atualizar esse treino depois com a UML.
        String local,
        LocalDate data,
        LocalTime horario
    
) {
        public static TreinoResponseDTO valueOf(Treino t){
        return new TreinoResponseDTO(
            t.getLocal(),
            t.getData(),
            t.getHorario()
        );
    }
}
