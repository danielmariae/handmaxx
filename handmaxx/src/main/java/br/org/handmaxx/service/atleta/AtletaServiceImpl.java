package br.org.handmaxx.service.atleta;

import java.util.List;
import java.util.stream.Collectors;

import br.org.handmaxx.app.error.custom.CustomException;
import br.org.handmaxx.app.error.global.ErrorResponse;
import br.org.handmaxx.dto.atleta.AtletaDTO;
import br.org.handmaxx.dto.atleta.AtletaResponseDTO;
import br.org.handmaxx.model.Atleta;
import br.org.handmaxx.model.QuestionarioSocial;
import br.org.handmaxx.repository.AtletaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;

@ApplicationScoped
public class AtletaServiceImpl implements AtletaService {

    @Inject
    AtletaRepository atletaRepository;

    @Override
    public AtletaResponseDTO findById(Long id) {
        Atleta atleta = atletaRepository.findById(id);
        if (atleta == null) {
            throw new CustomException(new ErrorResponse("Atleta não encontrado", "AtletaServiceImpl(findById)", 404));
        }
        return AtletaResponseDTO.valueOf(atleta);
    }

    @Override
    public AtletaResponseDTO create(AtletaDTO dto) {
        Atleta atleta = new Atleta();

        atleta.setNome(dto.nome());
        atleta.setCpf(dto.cpf());
        atleta.setDataNascimento(dto.dataNascimento());
        atleta.setSexo(dto.sexo());
        atleta.setCategoria(dto.categoria());

        atleta.setEndereco(dto.endereco().toModel());

        // atleta.setDadosSociais(dto.questionario().toModel());
        QuestionarioSocial questionario = new QuestionarioSocial();
        questionario.setRendaFamiliar(dto.questionario().rendaFamiliar());
        questionario.setPessoasEmCasa(dto.questionario().pessoasEmCasa());
        questionario.setCondicoesMoradia(dto.questionario().condicoesMoradia());
        questionario.setCadastroNIS(dto.questionario().cadastroNIS());
        
        atleta.setDadosSociais(questionario);
        
        try {
            atletaRepository.persist(atleta);
        } catch (PersistenceException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    "Erro ao criar atleta",
                    "AtletaServiceImpl(create): " + e.getMessage(),
                    500);
            throw new CustomException(errorResponse);
        }
        return AtletaResponseDTO.valueOf(atleta);
    }

    @Override
    public AtletaResponseDTO update(AtletaDTO dto, Long id) {
        Atleta atleta = atletaRepository.findById(id);
        if (atleta == null) {
            throw new CustomException(new ErrorResponse("Atleta não encontrado", "AtletaServiceImpl(update)", 404));
        }

        atleta.setNome(dto.nome());
        atleta.setCpf(dto.cpf());
        atleta.setDataNascimento(dto.dataNascimento());
        atleta.setSexo(dto.sexo());
        atleta.setCategoria(dto.categoria());

        atleta.setEndereco(dto.endereco().toModel());
        atleta.setDadosSociais(dto.questionario().toModel());

        return AtletaResponseDTO.valueOf(atleta);
    }

    @Override
    public void delete(Long id) {
        Atleta atleta = atletaRepository.findById(id);
        if (atleta == null) {
            throw new CustomException(new ErrorResponse("Atleta não encontrado", "AtletaServiceImpl(delete)", 404));
        }
        try {
            atletaRepository.delete(atleta);
        } catch (Exception e) {
            throw new CustomException(new ErrorResponse("Erro no servidor.", "AtletaServiceImpl(delete): "+e.getMessage(), 500));
        }
    }

    @Override
    public List<AtletaResponseDTO> findByNome(String nome) {
        List<Atleta> atletas = atletaRepository.findByNome(nome);
        return atletas.stream()
                      .map(AtletaResponseDTO::valueOf)
                      .collect(Collectors.toList());
    }

}
