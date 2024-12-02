package br.org.handmaxx.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Publicacao extends DefaultEntity {

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "text")  // Garante compatibilidade com bancos como PostgreSQL
    private String conteudo;


    @Column(columnDefinition = "text")
    private String nomeImagem;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPublicacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario autor;
}
