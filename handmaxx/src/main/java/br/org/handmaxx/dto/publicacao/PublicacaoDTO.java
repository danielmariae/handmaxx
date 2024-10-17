package br.org.handmaxx.dto.publicacao;

import br.org.handmaxx.model.Publicacao;
import jakarta.validation.constraints.NotBlank;

public record PublicacaoDTO(
        @NotBlank(message = "O título não pode ser nulo.") 
        String titulo,

        @NotBlank(message = "O conteúdo não pode ser nulo.") 
        String conteudo,
        
        String nomeImagem
        ) 
        {
            
    public static PublicacaoDTO valueOf(Publicacao publicacao) {
        return new PublicacaoDTO(
                publicacao.getTitulo(),
                publicacao.getConteudo(),
                publicacao.getNomeImagem());
    }
}
