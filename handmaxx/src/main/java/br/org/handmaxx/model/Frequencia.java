package br.org.handmaxx.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Frequencia extends DefaultEntity {
    private Treino treino;
    private Atleta atleta;
    private boolean presenca;
}
