package br.org.handmaxx.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper= true)
public class QuestionarioSocial extends DefaultEntity {
    private Double rendaFamiliar;
    private Integer pessoasEmCasa;
    @Enumerated(EnumType.STRING)
    private CondicoesMoradia condicoesMoradia;
    private Boolean cadastroNIS;
}
