package br.org.handmaxx.dto.treino;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.org.handmaxx.dto.atleta.AtletaTreinoDTO;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TreinoCreateDTO(
    @NotBlank
    String local,
    @NotNull @FutureOrPresent(message = "Proibido marcar com datas no passado.") 
    @JsonFormat(pattern = "dd/MM/yyyy") // Define o formato específico para o campo
    LocalDate data,
    @NotNull  
    LocalTime horario,
    boolean criarTreinoTodosAtletas,
    List<AtletaTreinoDTO> listarAtletas
) {}
