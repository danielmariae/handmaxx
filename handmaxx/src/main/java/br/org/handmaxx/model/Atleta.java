package br.org.handmaxx.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Atleta extends DefaultEntity {
    private String nome;

    private String cpf;

    private LocalDate dataNascimento;

    private Sexo sexo; 

    private Categoria categoria;
}
