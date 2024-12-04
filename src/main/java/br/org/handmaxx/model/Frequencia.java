package br.org.handmaxx.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Frequencia extends DefaultEntity {
    
    @ManyToOne
    private Treino treino;

    @ManyToOne
    private Atleta atleta;

    private boolean presenca;
}
