package br.org.handmaxx.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper= true)
public class QuestionarioSocial extends DefaultEntity {
    private Double rendaFamiliar;
    private Integer pessoasEmCasa;
    private String condicoesMoradia;
    private Boolean cadastroNIS;
}
