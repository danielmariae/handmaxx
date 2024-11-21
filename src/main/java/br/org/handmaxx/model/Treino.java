package br.org.handmaxx.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;
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
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "atletas_treino", 
        joinColumns = @JoinColumn(name = "treino_id"), 
        inverseJoinColumns = @JoinColumn(name = "atleta_id")
    )
    private List<Atleta> listaAtletas;
    @OneToMany(mappedBy = "treino", cascade = CascadeType.ALL)
    private List<Frequencia> frequencia;

    @PreRemove
    public void removerAtletasAssociados() {
        if(frequencia != null){
            frequencia.clear();
        }
        if (listaAtletas != null) {
            listaAtletas.clear(); // Remove todas as associações
        }
    }   
}
