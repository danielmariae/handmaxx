package br.org.handmaxx.dto.publicacao;

import br.org.handmaxx.model.Publicacao;

public record PublicacaoFullResponseDTO(
        Long id,
        String titulo,
        String conteudo,
        String nomeImagem) {
    public static PublicacaoFullResponseDTO valueOf(Publicacao p) {
        return new PublicacaoFullResponseDTO(p.getId(), p.getTitulo(), p.getConteudo(), p.getNomeImagem());
    }
}
