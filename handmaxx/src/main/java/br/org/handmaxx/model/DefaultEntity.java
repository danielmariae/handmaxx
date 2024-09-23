package br.org.handmaxx.model;

import java.time.LocalDateTime;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.EqualsAndHashCode;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)  
public class DefaultEntity extends PanacheEntityBase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataHoraCriacao;
    private LocalDateTime dataHoraAtualizacao;

    @PrePersist
    protected void onCreate(){
        dataHoraCriacao = LocalDateTime.now();
        dataHoraAtualizacao = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate(){
        dataHoraAtualizacao = LocalDateTime.now();
    }
}
