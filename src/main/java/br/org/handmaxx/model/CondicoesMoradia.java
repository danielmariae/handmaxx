package br.org.handmaxx.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CondicoesMoradia {
    PESSIMA(0, "Condições subhumanas."),
    RUIM(1, "Condições ruins."),
    REGULAR(2, "Condições regulares."),
    BOA(3, "Condições boas."),
    EXCELENTE(4, "Condições excelentes.");

    private final Integer id;
    private final String descricao;
}