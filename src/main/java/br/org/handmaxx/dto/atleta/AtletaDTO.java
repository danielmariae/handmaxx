package br.org.handmaxx.dto.atleta;

import java.time.LocalDate;

import br.org.handmaxx.dto.atleta.questionariosocial.QuestionarioSocialDTO;
import br.org.handmaxx.dto.endereco.EnderecoDTO;
import br.org.handmaxx.model.Atleta;
//import br.org.handmaxx.model.Categoria;
import br.org.handmaxx.model.Sexo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Pattern;

public record AtletaDTO(

        @NotBlank 
        String nome,
        
        @NotBlank(message = "O campo cpf não pode ser nulo.")
        // @Pattern(regexp =
        // "([0-9]{3}[-./\s][0-9]{3}[-./\s][0-9]{3}[-./\s][0-9]{2})|([0-9]{11})",
        // message = "Valor digitado inválido!")
        String cpf,

        // @Pattern(regexp =
        // "([0-9]{4}[-/.\s][0-9]{2}[-/.\s][0-9]{2})|([0-9]{2}[/.\s][0-9]{2}[/.\s][0-9]{4})|([0-9]{8})",
        // message = "Informe uma data válida!")
        LocalDate dataNascimento,

        @NotNull(message = "O campo sexo não pode ser nulo.") 
        Sexo sexo,

        // @NotNull(message = "A categoria não pode ser nula.") 
        // Categoria categoria,
        
        @Valid
        EnderecoDTO endereco,

        @Valid
        QuestionarioSocialDTO questionario,

        // Telefone deve estar presente tanto no cadastro inicial quanto final
        @NotBlank(message = "O telefone não pode ser nulo.")
        String telefone
) {
    public static AtletaDTO valueOf(Atleta atleta) {
        return new AtletaDTO(
                atleta.getNome(),
                atleta.getCpf(),
                atleta.getDataNascimento(),
                atleta.getSexo(),
                //atleta.getCategoria(),
                EnderecoDTO.valueOf(atleta.getEndereco()),
                QuestionarioSocialDTO.valueOf(atleta.getDadosSociais()),
                atleta.getTelefone()
        );
    }
}
