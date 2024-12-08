package br.org.handmaxx.service.atleta;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.org.handmaxx.app.error.custom.CustomException;
import br.org.handmaxx.app.error.global.ErrorResponse;
import br.org.handmaxx.dto.atleta.AtletaCadastroInicialDTO;
import br.org.handmaxx.dto.atleta.AtletaDTO;
import br.org.handmaxx.dto.atleta.AtletaResponseDTO;
import br.org.handmaxx.dto.atleta.AtletaTreinoDTO;
import br.org.handmaxx.dto.mensagem.MensagemDTO;
import br.org.handmaxx.model.Atleta;
import br.org.handmaxx.model.CadastroAtletaToken;
import br.org.handmaxx.model.QuestionarioSocial;
import br.org.handmaxx.model.Treino;
import br.org.handmaxx.repository.AtletaRepository;
import br.org.handmaxx.repository.CadastroAtletaTokenRepository;
import br.org.handmaxx.repository.TreinoRepository;
import br.org.handmaxx.resource.WhatsappResource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
//import jakarta.validation.Valid;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AtletaServiceImpl implements AtletaService {

    @Inject
    AtletaRepository atletaRepository;

    @Inject
    CadastroAtletaTokenRepository cadastroTokenRepository;

    @Inject
    TreinoRepository treinoRepository;


    @Inject
    WhatsappResource whatsAppResource;

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

    @Override
    public AtletaResponseDTO create(AtletaDTO dto) {
        Atleta atleta = new Atleta();
        // Definir campos básicos do atleta
        atleta.setNome(dto.nome());
        atleta.setTelefone(dto.telefone());
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

        verificaCPF(dto.cpf());
        try {
            atletaRepository.persist(atleta);
        } catch (PersistenceException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    "Erro ao criar atleta: "+e.getCause().toString(),
                    "AtletaServiceImpl(create)",
                    500);
            throw new CustomException(errorResponse);
        }

        return AtletaResponseDTO.valueOf(atleta);
    }

    @Override
    @Transactional
    public AtletaResponseDTO createInitialWithTreino(AtletaCadastroInicialDTO dto, Long treinoId) {
        Atleta atleta = new Atleta();
        atleta.setNome(dto.nome());
        atleta.setTelefone(dto.telefone()); // Usar o telefone no lugar do CPF
        atleta.setDataNascimento(dto.dataNascimento());
        atleta.atualizarCategoria(); // Atualizar a categoria com base na idade
        atleta.setCadastroCompleto(false); // Definir como cadastro incompleto
        
        try {
            atletaRepository.persist(atleta);
        } catch (PersistenceException e) {
            throw new CustomException(new ErrorResponse("Erro ao criar atleta: "+e.getCause().getMessage(),
                    "AtletaServiceImpl(createInitial)", 500));
        }
    
        if(dto.enviarCadastroTelefone()){
            gerarTokenCadastro(atleta);
        }

        Treino treino = treinoRepository.findById(treinoId);
        List<Atleta> atletas = treino.getListaAtletas();
        atletas.add(atleta);
        treino.setListaAtletas(atletas);
        
        try {
            treinoRepository.persist(treino);
        } catch (PersistenceException e) {
            throw new CustomException(new ErrorResponse("Erro ao criar atleta: "+e.getCause().getMessage(),
                    "AtletaServiceImpl(createInitial)", 500));
        }

        return AtletaResponseDTO.valueOf(atleta);
    }

    @Override
    @Transactional
    public AtletaResponseDTO createInitial(AtletaCadastroInicialDTO dto) {
        Atleta atleta = new Atleta();
        atleta.setNome(dto.nome());
        atleta.setTelefone(dto.telefone()); // Usar o telefone no lugar do CPF
        atleta.setDataNascimento(dto.dataNascimento());
        atleta.atualizarCategoria(); // Atualizar a categoria com base na idade
        atleta.setCadastroCompleto(false); // Definir como cadastro incompleto
        
        try {
            atletaRepository.persist(atleta);
        } catch (PersistenceException e) {
            throw new CustomException(new ErrorResponse("Erro ao criar atleta: "+e.getCause().getMessage(),
                    "AtletaServiceImpl(createInitial)", 500));
        }
    
        if(dto.enviarCadastroTelefone()){
            gerarTokenCadastro(atleta);
        }

        return AtletaResponseDTO.valueOf(atleta);
    }

    @Override
    @Transactional
    public AtletaResponseDTO update(AtletaDTO dto, Long id) {
        Atleta atleta = atletaRepository.findById(id);

        if (atleta == null) {
            throw new CustomException(new ErrorResponse("Atleta não encontrado.", "AtletaServiceImpl(update)", 404));
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
        if(!atleta.isCadastroCompleto()){
            atleta.setCadastroCompleto(true); // Marcar como completo após o update
        }

        try {
            atletaRepository.persist(atleta);
        } catch (PersistenceException e) {
            throw new CustomException(
                    new ErrorResponse("Erro ao atualizar atleta: "+e.getCause().toString(), "AtletaServiceImpl(update): " + e.getMessage(), 500));
        }

        return AtletaResponseDTO.valueOf(atleta);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Atleta atleta = atletaRepository.findById(id);
        if (atleta == null) {
            throw new CustomException(new ErrorResponse("Atleta não encontrado.", "AtletaServiceImpl(delete)", 404));
        }
        try {
            atletaRepository.delete(atleta);
        } catch (Exception e) {
            throw new CustomException(new ErrorResponse("Erro no servidor: "+e.getCause().toString(), "AtletaServiceImpl(delete)", 500));
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

    @Override
    public List<AtletaTreinoDTO> findAllTreinos() {
        List<Atleta> atletas = atletaRepository.listAll();  // Usar PanacheRepository listAll
        return atletas.stream()
                      .map(AtletaTreinoDTO::valueOf)
                      .collect(Collectors.toList());
    }

    @Override
    public AtletaResponseDTO completarCadastroToken(AtletaDTO dto, String token){
        Atleta atleta = atletaRepository.findAtletaByToken(token);
        CadastroAtletaToken cadastroToken = cadastroTokenRepository.findByToken(token);

        if(!validarToken(token)){
            throw new CustomException(new ErrorResponse("Token de cadastro inválido e/ou expirado.", "AtletaServiceImpl(completarCadastroToken)", 401));
        }
        if (atleta == null) {
            throw new CustomException(new ErrorResponse("Atleta não encontrado", "AtletaServiceImpl(completarCadastroToken)", 404));
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

        // Marcar token como utilizado
        cadastroToken.setUtilizado(true);
        cadastroTokenRepository.persist(cadastroToken);
        
        try {
            atletaRepository.persist(atleta);
        } catch (PersistenceException e) {
            throw new CustomException(
                    new ErrorResponse("Erro ao excluir atleta: "+e.getMessage(), "AtletaServiceImpl(update)", 500));
        }

        return AtletaResponseDTO.valueOf(atleta);

    }

    @Override
    public void gerarTokenCadastro(Long atletaId) {
        Atleta atleta = atletaRepository.findById(atletaId);

        if (atleta == null) {
            throw new CustomException(new ErrorResponse("Atleta não encontrado", "AtletaServiceImpl(gerarTokenCadastro)", 404));
        }

        CadastroAtletaToken tokenAnterior = cadastroTokenRepository.findByAtleta(atletaId);

        if(tokenAnterior != null){
            cadastroTokenRepository.delete(tokenAnterior);
        }

        // Criar token único e data de expiração
        String token = UUID.randomUUID().toString();
        LocalDateTime dataExpiracao = LocalDateTime.now().plusHours(48);

        CadastroAtletaToken cadastroToken = new CadastroAtletaToken();
        cadastroToken.setToken(token);
        cadastroToken.setDataExpiracao(dataExpiracao);
        cadastroToken.setUtilizado(false);
        cadastroToken.setAtleta(atleta);

        cadastroTokenRepository.persist(cadastroToken);

        // Montar URL para o cadastro
        String urlCadastro = "http://localhost:8100/completar-cadastro/" + token;

        // Enviar mensagem via WhatsApp
        String mensagem = String.format("Olá %s, complete seu cadastro clicando no link abaixo: %s\n*Você tem 48h para completar seu cadastro.*\nAtenciosamente,\nEquipe Handmaxx.", 
                                         atleta.getNome(), urlCadastro);

        
        whatsAppResource.sendTextMessage(new MensagemDTO(retirarPrimeiroNove(atleta.getTelefone()), mensagem, "default"));
    }

    private void gerarTokenCadastro(Atleta atleta) {
        if (atleta == null) {
            throw new CustomException(new ErrorResponse("Atleta não encontrado", "AtletaServiceImpl(completarCadastroToken)", 404));
        }

        CadastroAtletaToken tokenAnterior = cadastroTokenRepository.findByAtleta(atleta.getId());

        if(tokenAnterior != null){
            cadastroTokenRepository.delete(tokenAnterior);
        }

        // Criar token único e data de expiração
        String token = UUID.randomUUID().toString();
        LocalDateTime dataExpiracao = LocalDateTime.now().plusHours(48);

        CadastroAtletaToken cadastroToken = new CadastroAtletaToken();
        cadastroToken.setToken(token);
        cadastroToken.setDataExpiracao(dataExpiracao);
        cadastroToken.setUtilizado(false);
        cadastroToken.setAtleta(atleta);

        cadastroTokenRepository.persist(cadastroToken);

        System.out.println(cadastroToken.getToken());
        // Montar URL para o cadastro
        String urlCadastro = "http://localhost:8100/completar-cadastro/" + token;

        // Enviar mensagem via WhatsApp
        String mensagem = String.format("Olá atleta %s, complete seu cadastro no Handmaxx clicando no link abaixo:\n%s\n\n*Você tem 48h para completar seu cadastro.*\n\nAtenciosamente,\nEquipe Handmaxx.", 
                                         atleta.getNome(), urlCadastro);

        
        whatsAppResource.sendTextMessage(new MensagemDTO("55"+retirarPrimeiroNove(atleta.getTelefone())+"@c.us", mensagem, "default"));
    }


    @Override
    public boolean validarToken(String token) {
        CadastroAtletaToken cadastroToken = cadastroTokenRepository.findByToken(token);

        if (cadastroToken == null) {
            return false;
        }

        if (cadastroToken.isExpirado()) {
            return false;
        }

        if (cadastroToken.isUtilizado()) {
            return false;
        }

        return true;
    }

    @Override
    public Long countTodosAtletas(){
        return atletaRepository.count();
    }

    private String retirarPrimeiroNove(String numero){

        if (numero == null || numero.length() <= 10) {
            return numero; 
        }
        
        int indiceDoNove = numero.indexOf('9', 2); 
        if (indiceDoNove == 2) {
            return numero.substring(0, 2) + numero.substring(3);
        }
        return numero; 
    }

    private void verificaCPF(String cpf) {
        Atleta atleta = atletaRepository.findByCpf(cpf);
        if(atleta != null){
            throw new CustomException(new ErrorResponse("Atleta com CPF cadastro já existe.", "AtletaServiceImpl(update)", 404));
        }
    }
}
