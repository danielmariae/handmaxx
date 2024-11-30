package br.org.handmaxx.dto.publicacao;

import br.org.handmaxx.model.Publicacao;
import org.jsoup.Jsoup;

import java.util.List;

public record PublicacaoResponseDTO(
        Long id,
        String titulo,
        List<String> conteudos,
        List<String> nomeImagens) {

    public static PublicacaoResponseDTO valueOf(Publicacao p) {
        return new PublicacaoResponseDTO(
                p.getId(),
                p.getTitulo(),
                limitaConteudos(removeHtmlTags(p.getConteudos())),
                p.getNomeImagens());
    }

    public static List<String> removeHtmlTags(List<String> conteudos) {
        return conteudos.stream()
                .map(c -> Jsoup.parse(c).text())
                .toList();
    }

    public static List<String> limitaConteudos(List<String> conteudos) {
        return conteudos.stream()
                .map(c -> c.length() <= 1000 ? c : c.substring(0, 200))
                .toList();
    }
}
