package br.org.handmaxx.dto.atleta;

import java.time.LocalDate;

import br.org.handmaxx.model.Atleta;

public record AtletaTreinoDTO(
    Long id,
    String nome,
    String categoria,
    LocalDate dataNascimento
    ) 
{      
    public static AtletaTreinoDTO valueOf(Atleta a){
        return new AtletaTreinoDTO(a.getId(), a.getNome(), a.getCategoria().getNome(), a.getDataNascimento());
    }
}
