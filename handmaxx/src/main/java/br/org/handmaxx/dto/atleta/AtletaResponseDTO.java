package br.org.handmaxx.dto.atleta;

import java.time.LocalDate;

import br.org.handmaxx.model.Atleta;
import br.org.handmaxx.model.Categoria;
import br.org.handmaxx.model.Sexo;

public record AtletaResponseDTO(
    String nome,
    String cpf,
    LocalDate dataNascimento,
    Sexo sexo, 
    Categoria categoria
) {
    public static AtletaResponseDTO valueOf(Atleta atleta){
        return new AtletaResponseDTO(
            atleta.getNome(),
            atleta.getCpf(), 
            atleta.getDataNascimento(), 
            atleta.getSexo(),
            atleta.getCategoria()
        );
    }
}
