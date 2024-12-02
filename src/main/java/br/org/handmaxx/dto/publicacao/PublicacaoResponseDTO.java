package br.org.handmaxx.dto.publicacao;

import br.org.handmaxx.model.Publicacao;
import org.jsoup.Jsoup;

public record PublicacaoResponseDTO(
        Long id,
        String titulo,
        String conteudo,
        String nomeImagem) {

    public static PublicacaoResponseDTO valueOf(Publicacao p) {
        return new PublicacaoResponseDTO(
                p.getId(),
                p.getTitulo(),
                limitaConteudo(removeHtmlTags(p.getConteudo())),
                p.getNomeImagem());
    }

    public static String removeHtmlTags(String conteudo) {
        return Jsoup.parse(conteudo).text();
    }

    public static String limitaConteudo(String conteudo) {
        return conteudo.length() <= 1000 ? conteudo : conteudo.substring(0, 200);
    }
}
