package br.org.handmaxx.dto.noticiaHome;

import br.org.handmaxx.model.NoticiaHome;
import jakarta.validation.constraints.NotBlank;

public record NoticiaHomeDTO(
        @NotBlank(message = "O título não pode ser nulo.") 
        String titulo,

        @NotBlank(message = "O conteúdo não pode ser nulo.") 
        String conteudo) 
        {
            
    public static NoticiaHomeDTO valueOf(NoticiaHome noticia) {
        return new NoticiaHomeDTO(noticia.getTitulo(), noticia.getConteudo());
    }
}
