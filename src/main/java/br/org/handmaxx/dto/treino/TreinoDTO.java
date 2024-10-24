package br.org.handmaxx.dto.treino;

import java.time.LocalDate;
import java.time.LocalTime;

import br.org.handmaxx.model.Treino;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TreinoDTO(
    // Atualizar esse treino depois com a UML.
    @NotBlank
    String local,
    @NotNull @FutureOrPresent(message = "Proibido marcar com datas no passado.")
    LocalDate data,
    @NotNull  
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
