package br.org.handmaxx.dto.atleta;

import java.time.LocalDate;

import br.org.handmaxx.dto.atleta.questionariosocial.QuestionarioSocialResponseDTO;
import br.org.handmaxx.dto.endereco.EnderecoResponseDTO;
import br.org.handmaxx.model.Atleta;
import br.org.handmaxx.model.Categoria;
import br.org.handmaxx.model.Sexo;

public record AtletaResponseDTO(
        Long id,
        String nome,
        String telefone,
        String cpf,
        LocalDate dataNascimento,
        Sexo sexo,
        Categoria categoria,
        EnderecoResponseDTO endereco,
        QuestionarioSocialResponseDTO dadosSociais

) {
    public static AtletaResponseDTO valueOf(Atleta atleta) {
        return new AtletaResponseDTO(
                atleta.getId(),
                atleta.getNome(),
                atleta.getTelefone(),
                atleta.getCpf(),
                atleta.getDataNascimento(),
                atleta.getSexo(),
                atleta.getCategoria(),
                // EnderecoResponseDTO.valueOf(atleta.getEndereco()),
                // QuestionarioSocialResponseDTO.valueOf(atleta.getDadosSociais())
                atleta.getEndereco() != null ? EnderecoResponseDTO.valueOf(atleta.getEndereco()) : null, // Verifique se endereco não é nulo
                atleta.getDadosSociais() != null ? QuestionarioSocialResponseDTO.valueOf(atleta.getDadosSociais()): null // Verifique se dadosSociais não é nulo
        );
    }
}
