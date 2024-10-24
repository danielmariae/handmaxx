package br.org.handmaxx.service.atleta;

import java.util.List;
import java.util.stream.Collectors;

import br.org.handmaxx.app.error.custom.CustomException;
import br.org.handmaxx.app.error.global.ErrorResponse;
import br.org.handmaxx.dto.atleta.AtletaCadastroInicialDTO;
import br.org.handmaxx.dto.atleta.AtletaDTO;
import br.org.handmaxx.dto.atleta.AtletaResponseDTO;
import br.org.handmaxx.model.Atleta;
import br.org.handmaxx.model.QuestionarioSocial;
import br.org.handmaxx.repository.AtletaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
//import jakarta.validation.Valid;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AtletaServiceImpl implements AtletaService {

    @Inject
    AtletaRepository atletaRepository;

    @Override
    public AtletaResponseDTO findById(Long id) {
        Atleta atleta = atletaRepository.findById(id);
        if (atleta == null) {
            // Adicionando log para debugar o ID
            System.out.println("Atleta não encontrado para o ID: " + id);
            throw new CustomException(new ErrorResponse("Atleta não encontrado", "AtletaServiceImpl(findById)", 404));
        }
        return AtletaResponseDTO.valueOf(atleta);
    }

    // @Override
    // public AtletaResponseDTO create(AtletaDTO dto) {
    // Atleta atleta = new Atleta();

    // atleta.setNome(dto.nome());
    // atleta.setCpf(dto.cpf());
    // atleta.setDataNascimento(dto.dataNascimento());
    // atleta.setSexo(dto.sexo());
    // atleta.setCategoria(dto.categoria());

    // atleta.setEndereco(dto.endereco().toModel());

    // // atleta.setDadosSociais(dto.questionario().toModel());
    // QuestionarioSocial questionario = new QuestionarioSocial();
    // questionario.setRendaFamiliar(dto.questionario().rendaFamiliar());
    // questionario.setPessoasEmCasa(dto.questionario().pessoasEmCasa());
    // questionario.setCondicoesMoradia(dto.questionario().condicoesMoradia());
    // questionario.setCadastroNIS(dto.questionario().cadastroNIS());

    // atleta.setDadosSociais(questionario);

    // try {
    // atletaRepository.persist(atleta);
    // } catch (PersistenceException e) {
    // ErrorResponse errorResponse = new ErrorResponse(
    // "Erro ao criar atleta",
    // "AtletaServiceImpl(create): " + e.getMessage(),
    // 500);
    // throw new CustomException(errorResponse);
    // }
    // return AtletaResponseDTO.valueOf(atleta);
    // }

    @Override
    public AtletaResponseDTO create(AtletaDTO dto) {
        Atleta atleta = new Atleta();
        // Definir campos básicos do atleta
        // atleta.setNome(dto.nome());
        atleta.setCpf(dto.cpf());
        atleta.setDataNascimento(dto.dataNascimento());
        atleta.setSexo(dto.sexo());
        atleta.atualizarCategoria();// Atualizar automaticamente a categoria com base na data de nascimento

        // Endereço e questionário
        atleta.setEndereco(dto.endereco().toModel());

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
    @Transactional
    public AtletaResponseDTO createInitial(AtletaCadastroInicialDTO dto) {
        Atleta atleta = new Atleta();
        atleta.setNome(dto.nome());
        atleta.setTelefone(dto.telefone()); // Usar o telefone no lugar do CPF
        // atleta.setCpf(dto.cpf());
        atleta.setDataNascimento(dto.dataNascimento());
        atleta.atualizarCategoria(); // Atualizar a categoria com base na idade
        atleta.setCadastroCompleto(false); // Definir como cadastro incompleto

        try {
            atletaRepository.persist(atleta);
        } catch (PersistenceException e) {
            throw new CustomException(new ErrorResponse("Erro ao criar atleta",
                    "AtletaServiceImpl(createInitial): " + e.getMessage(), 500));
        }
        return AtletaResponseDTO.valueOf(atleta);
    }

    @Override
    @Transactional
    public AtletaResponseDTO update(AtletaDTO dto, Long id) {
        Atleta atleta = atletaRepository.findById(id);

        if (atleta == null) {
            throw new CustomException(new ErrorResponse("Atleta não encontrado", "AtletaServiceImpl(update)", 404));
        }

        // Atualizar apenas os campos que foram enviados e não são nulos
        if (dto.nome() != null) {
            atleta.setNome(dto.nome());
        }
        if (dto.cpf() != null) {
            atleta.setCpf(dto.cpf());
        }
        if (dto.dataNascimento() != null) {
            atleta.setDataNascimento(dto.dataNascimento());
        }
        if (dto.sexo() != null) {
            atleta.setSexo(dto.sexo());
        }
        if (dto.telefone() != null) {
            atleta.setTelefone(dto.telefone());
        }

        // Atualizar Endereço apenas se fornecido
        if (dto.endereco() != null) {
            atleta.setEndereco(dto.endereco().toModel());
        }

        // Atualizar Questionário Social apenas se fornecido
        if (dto.questionario() != null) {
            atleta.setDadosSociais(dto.questionario().toModel());
        }

        atleta.atualizarCategoria();
        atleta.setCadastroCompleto(true); // Marcar como completo após o update

        try {
            atletaRepository.persist(atleta);
        } catch (PersistenceException e) {
            throw new CustomException(
                    new ErrorResponse("Erro ao atualizar atleta", "AtletaServiceImpl(update): " + e.getMessage(), 500));
        }

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
            throw new CustomException(
                    new ErrorResponse("Erro no servidor.", "AtletaServiceImpl(delete): " + e.getMessage(), 500));
        }
    }

    @Override
    public List<AtletaResponseDTO> findByNome(String nome) {
        List<Atleta> atletas = atletaRepository.findByNome(nome);
        if (atletas == null) {
            throw new CustomException(
                    new ErrorResponse("Nenhum atleta encontrado", "AtletaServiceImpl(findByNome)", 404));
        }
        return atletas.stream()
                .map(AtletaResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<AtletaResponseDTO> findAll() {
        List<Atleta> atletas = atletaRepository.listAll();  // Usar PanacheRepository listAll
        return atletas.stream()
                      .map(AtletaResponseDTO::valueOf)
                      .collect(Collectors.toList());
    }
}
