package br.org.handmaxx.dto.publicacao;

import br.org.handmaxx.model.Publicacao;

public record PublicacaoResponseDTO(
        Long id,
        String titulo,
        String conteudo,
        String nomeImagem
) {
    public static PublicacaoResponseDTO valueOf(Publicacao p){
        return new PublicacaoResponseDTO(p.getId(), p.getTitulo(), p.getConteudo(), p.getNomeImagem());
    }
}
