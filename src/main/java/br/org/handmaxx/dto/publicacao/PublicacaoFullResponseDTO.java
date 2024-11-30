package br.org.handmaxx.dto.publicacao;

import br.org.handmaxx.model.Publicacao;

import java.time.LocalDateTime;
import java.util.List;

public record PublicacaoFullResponseDTO(
        Long id,
        String titulo,
        List<String> conteudos,
        List<String> nomeImagens,
        LocalDateTime dataPublicacao) {

    public static PublicacaoFullResponseDTO valueOf(Publicacao p) {
        return new PublicacaoFullResponseDTO(
                p.getId(),
                p.getTitulo(),
                p.getConteudos(),
                p.getNomeImagens(),
                p.getDataPublicacao());
    }
}
