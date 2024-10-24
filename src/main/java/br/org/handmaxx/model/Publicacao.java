package br.org.handmaxx.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Publicacao extends DefaultEntity {
    
    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String conteudo;

    private String nomeImagem;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPublicacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario autor;
}
