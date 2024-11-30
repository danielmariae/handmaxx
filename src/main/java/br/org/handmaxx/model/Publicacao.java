package br.org.handmaxx.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ElementCollection;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;  // Importando LocalDateTime
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Publicacao extends DefaultEntity {

    @Column(nullable = false)
    private String titulo;

    // Usando List<String> para armazenar múltiplos conteúdos
    @ElementCollection
    @Column(columnDefinition = "text")  // Garante compatibilidade com bancos como PostgreSQL
    private List<String> conteudos;

    // Usando List<String> para armazenar múltiplos nomes de imagens
    @ElementCollection
    @Column(columnDefinition = "text")
    private List<String> nomeImagens;

    // Alteração aqui para LocalDateTime
    private LocalDateTime dataPublicacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario autor;

    // Construtores, getters e setters são gerados automaticamente pelo Lombok.
}
