package br.org.handmaxx.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Categoria {
    FORMACAO_BASICA(0, "Formação Básica", "Até 12 anos", 12), 
    TREINAMENTO_BASICO(1, "Infantil", "13 e 14 anos", 14), 
    TREINAMENTO_DE_FORMACAO(2, "Cadete","15 e 16 anos", 16), 
    JUVENIL(3, "Juvenil", "17 e 18 anos", 18), 
    JUNIOR(4, "Junior", "19 e 20 anos [fem.] e 19 a 21 anos [masc.]", 21), 
    ADULTA(5, "Adulta", "Acima de 20 [fem.] e 21 anos [masc.]", Integer.MAX_VALUE);

    private final Integer id;
    private final String nome;
    private final String descricao;
    private final int idadeLimite;

    // Método para obter a categoria com base na idade
    public static Categoria getCategoriaPorIdade(int idade) {
        for (Categoria categoria : Categoria.values()) {
            if (idade <= categoria.getIdadeLimite()) {
                return categoria;
            }
        }
        return ADULTA; // Caso a idade ultrapasse o limite, a categoria será "Adulta"
    }
}
