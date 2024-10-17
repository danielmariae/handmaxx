package br.org.handmaxx.model;

//import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Frequencia extends DefaultEntity {
    
    //@OneToOne(cascade = CascadeType.PERSIST)
    @ManyToOne
    private Treino treino;

    //@OneToOne(cascade = CascadeType.PERSIST)
    @ManyToOne
    private Atleta atleta;

    private boolean presenca;
}
