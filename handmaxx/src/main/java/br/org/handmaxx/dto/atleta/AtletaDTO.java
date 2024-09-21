package br.org.handmaxx.dto.atleta;

import java.time.LocalDate;

import br.org.handmaxx.model.Atleta;
import br.org.handmaxx.model.Categoria;
import br.org.handmaxx.model.Sexo;

public record AtletaDTO(
    String nome,
    String cpf,
    LocalDate dataNascimento,
    Sexo sexo, 
    Categoria categoria
) {
    public static AtletaDTO valueOf(Atleta atleta){
        return new AtletaDTO(
            atleta.getNome(),
            atleta.getCpf(), 
            atleta.getDataNascimento(), 
            atleta.getSexo(),
            atleta.getCategoria()
        );
    }
}
