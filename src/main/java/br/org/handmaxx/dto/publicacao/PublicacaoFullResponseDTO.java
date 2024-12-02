package br.org.handmaxx.dto.publicacao;

import br.org.handmaxx.model.Publicacao;
import java.util.Date;

public record PublicacaoFullResponseDTO(
        Long id,
        String titulo,
        String conteudo,
        String nomeImagem,
        Date dataPublicacao) {

    public static PublicacaoFullResponseDTO valueOf(Publicacao p) {
        return new PublicacaoFullResponseDTO(
                p.getId(),
                p.getTitulo(),
                p.getConteudo(),
                p.getNomeImagem(),
                p.getDataPublicacao());
    }
}
