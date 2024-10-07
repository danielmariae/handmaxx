package br.org.handmaxx.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Atleta extends DefaultEntity {

    private String nome;

    @Column(unique = true)
    private String cpf;

    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private Sexo sexo; 

    @OneToOne(cascade = CascadeType.PERSIST)
    private Endereco endereco;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    private QuestionarioSocial dadosSociais;

    @Enumerated(EnumType.ORDINAL)
    private Categoria categoria;
}
