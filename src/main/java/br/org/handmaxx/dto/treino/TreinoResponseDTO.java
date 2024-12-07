package br.org.handmaxx.dto.treino;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.org.handmaxx.model.Treino;

public record TreinoResponseDTO(
        // Atualizar esse treino depois com a UML.
        Long id,
        String local,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
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
