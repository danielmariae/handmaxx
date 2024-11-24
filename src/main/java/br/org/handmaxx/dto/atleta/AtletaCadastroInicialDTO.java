package br.org.handmaxx.dto.atleta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record AtletaCadastroInicialDTO(
        @NotBlank (message = "Nome não pode ser nulo.")
        String nome,
        // Campo de telefone para o cadastro inicial
        @NotBlank(message = "O telefone não pode ser nulo.")
        String telefone,
        @NotNull(message = "A data de nascimento não pode ser nula.")
        LocalDate dataNascimento,
        Boolean enviarCadastroTelefone
        ) {

}
