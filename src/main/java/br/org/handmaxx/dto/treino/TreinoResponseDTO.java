package br.org.handmaxx.dto.treino;

import java.time.LocalDateTime;

import br.org.handmaxx.model.Treino;

public record TreinoResponseDTO(
        // Atualizar esse treino depois com a UML.
        Long id,
        String local,
        LocalDateTime dataHorario    
) {
        public static TreinoResponseDTO valueOf(Treino t){
        return new TreinoResponseDTO(
            t.getId(),
            t.getLocal(),
            t.getDataHorario()
        );
    }
}
