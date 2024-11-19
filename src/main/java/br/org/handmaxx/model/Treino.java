package br.org.handmaxx.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Treino extends DefaultEntity {

    private String local;
    // Atualizar esse treino depois com a UML.
    private LocalDate data;
    private LocalTime horario;
    private List<Atleta> listaAtletas;
    @OneToMany(mappedBy = "treino")
    private List<Frequencia> frequencia;
}
