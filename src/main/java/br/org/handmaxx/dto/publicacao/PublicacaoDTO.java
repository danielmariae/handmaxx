package br.org.handmaxx.dto.publicacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import br.org.handmaxx.model.Publicacao;
import jakarta.validation.constraints.NotBlank;

public record PublicacaoDTO(
        @NotBlank(message = "O título não pode ser nulo.")
        String titulo,
        List<String> conteudos,
        List<String> nomeImagens,
        LocalDateTime dataPublicacao) {

    public static PublicacaoDTO valueOf(Publicacao publicacao) {
        
        return new PublicacaoDTO(
                publicacao.getTitulo(),
                publicacao.getConteudos(),
                publicacao.getNomeImagens(),
                publicacao.getDataPublicacao());  
    }
}
