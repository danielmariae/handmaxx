package br.org.handmaxx.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Sexo {
    MASCULINO(0, "Masculino"),
    FEMININO(1, "Feminino"),
    OUTROS(2, "Outros");

    private final Integer id;
    private final String descricao;
}
