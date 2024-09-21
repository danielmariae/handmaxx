package br.org.handmaxx.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Categoria {
    FORMACAO_BASICA(0, "Formação Básica - até 12 anos."),   // até 12 anos
    TREINAMENTO_BASICO(1, "Infantil - 13 e 14 anos."), // 13-14 anos (Infantil)
    TREINAMENTO_DE_FORMACAO(2, "Cadete - 15 e 16 anos."), // 15-16 anos (Cadete)
    JUVENIL(3, "Juvenil - 17 e 18 anos."),            // 17-18 anos
    JUNIOR(4, "Junior - 19 e 20 anos [fem.] e 19 a 21 anos [masc.]."),             // Feminino (19-20 anos) e Masculino (19-21 anos)
    ADULTA(5, "Adulta - acima de 18 anos.");              // acima de 18 anos
    
    private final Integer id;
    private final String descricao;
}
