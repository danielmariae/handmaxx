package br.org.handmaxx.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificacaoAntes {
    TRINTA_MINUTOS(0, "30 minutos"),
    UMA_HORA(1, "1 hora (60 minutos)"),
    DUAS_HORAS(2, "2 horas"),
    OITO_HORAS(3, "8 horas"),
    DOZE_HORAS(4, "12 horas"),
    VINTE_QUATRO_HORAS(5, "24 horas");

    private final Integer id;
    private final String descricao;
}
