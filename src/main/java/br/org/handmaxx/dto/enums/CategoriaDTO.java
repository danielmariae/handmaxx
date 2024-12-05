package br.org.handmaxx.dto.enums;

import br.org.handmaxx.model.Categoria;

public record CategoriaDTO(
    Integer id, 
    String nome,
    String descricao,
    String enumName
) {
    public static CategoriaDTO valueOf(Categoria c){
        return new CategoriaDTO(c.getId(), c.getNome(), c.getDescricao(), c.toString()); 
    }
}
