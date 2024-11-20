package br.org.handmaxx.service.treino;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import br.org.handmaxx.app.error.custom.CustomException;
import br.org.handmaxx.app.error.global.ErrorResponse;
import br.org.handmaxx.dto.atleta.AtletaTreinoDTO;
import br.org.handmaxx.dto.mensagem.MensagemDTO;
import br.org.handmaxx.dto.treino.TreinoDTO;
import br.org.handmaxx.dto.treino.TreinoFullResponseDTO;
import br.org.handmaxx.dto.treino.TreinoCreateDTO;
import br.org.handmaxx.dto.treino.TreinoResponseDTO;
import br.org.handmaxx.model.Atleta;
import br.org.handmaxx.model.Treino;
import br.org.handmaxx.repository.AtletaRepository;
import br.org.handmaxx.repository.TreinoRepository;
import br.org.handmaxx.resource.WhatsappResource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TreinoServiceImpl implements TreinoService {

    @Inject
    TreinoRepository treinoRepository;

    @Inject 
    AtletaRepository atletaRepository;

    @Inject
    WhatsappResource whatsAppResource;
    
    @Override
    @Transactional
    public TreinoFullResponseDTO create(TreinoCreateDTO treinoDTO) {
        Treino treino = new Treino();
        
        treino.setLocal(treinoDTO.local());
        treino.setData(treinoDTO.data());
        treino.setHorario(treinoDTO.horario());
        

        if(treinoDTO.criarTreinoTodosAtletas()){
            List<Atleta> todosAtletas = atletaRepository.findAll().list();
            treino.setListaAtletas(todosAtletas);}
        else{
            List<Long> ids = treinoDTO.listarAtletas().stream().map(AtletaTreinoDTO::id).toList();
            List<Atleta> atletasEncontrados = atletaRepository.findByIds(ids);
            List<Long> idsNaoEncontrados = ids.stream()
                                                .filter(id -> atletasEncontrados.stream()
                                                .noneMatch(atleta -> atleta.getId().equals(id)))
                                                .toList();
            
            if (!idsNaoEncontrados.isEmpty()) {
                throw new CustomException(new ErrorResponse(
                    "Atletas não encontrados para os ID's: " + String.join(", ", idsNaoEncontrados.toString()),
                "TreinoService(criarTreino)",
                404
                ));

            }
            
            treino.setListaAtletas(atletasEncontrados);
        }
        try {
            treinoRepository.persist(treino);
        } catch (PersistenceException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    "Erro ao criar treino",
                    "TreinoServiceImpl(create): " + e.getMessage(),
                    500);
            throw new CustomException(errorResponse);
        }
        
        // notificarTodosAtletasCreate(treino);

        return TreinoFullResponseDTO.valueOf(treino);
    }

    @Override
    public void delete(Long id) {
        Treino treino = treinoRepository.findById(id);

        if(treino == null){
            throw new CustomException(new ErrorResponse("Treino não encontrado", "TreinoServiceImpl(delete)", 404));
        }
        try {
            // notificarTodosAtletasDelete(treino);
            treinoRepository.delete(treino);
        } catch (Exception e) {
            throw new CustomException(new ErrorResponse("Erro no servidor.", "TreinoServiceImpl(delete): "+e.getMessage(), 500));
        }
    }

    @Override
    public TreinoFullResponseDTO findById(Long id) {
        Treino treino = treinoRepository.findById(id);
        if(treino == null){
            throw new CustomException(new ErrorResponse("Treino não encontrado", "TreinoServiceImpl(findById)", 404));
        }
        return TreinoFullResponseDTO.valueOf(treino);
    }

    @Override
    public List<TreinoResponseDTO> findAll(){
        List<Treino> treinos = treinoRepository.findAll().list();
        return treinos.stream().map(TreinoResponseDTO::valueOf).collect(Collectors.toList());

    }
    @Override
    @Transactional
    public TreinoResponseDTO update(TreinoDTO dto, Long id) {
        Treino treino = treinoRepository.findById(id);
        if (treino == null) {
            throw new CustomException(new ErrorResponse("Treino não encontrado", "TreinoServiceImpl(update)", 404));
        }

        if(dto.local()!=null)
            treino.setLocal(dto.local());
        if(dto.data()!=null)
            treino.setData(dto.data());
        if(dto.horario()!=null)
            treino.setHorario(dto.horario());

            return TreinoResponseDTO.valueOf(treino);
    }    

    private void notificarTodosAtletasCreate(Treino treino){
        List<Atleta> atletas = atletaRepository.findAll().list();

        for(Atleta atleta : atletas){
            whatsAppResource.sendTextMessage(new MensagemDTO("55"+retirarPrimeiroNove(atleta.getTelefone())+"@c.us", criarMensagemTreinoCadastro(atleta, treino), "default"));
            System.out.println("Enviado para "+retirarPrimeiroNove(atleta.getTelefone())+"!");
        }
    }

    private void notificarTodosAtletasDelete(Treino treino){
        List<Atleta> atletas = atletaRepository.findAll().list();

        for(Atleta atleta : atletas){
            whatsAppResource.sendTextMessage(new MensagemDTO("55"+retirarPrimeiroNove(atleta.getTelefone())+"@c.us", criarMensagemTreinoCancelado(atleta, treino), "default"));
            System.out.println("Enviado para "+retirarPrimeiroNove(atleta.getTelefone())+"!");
        }
    }

    private void notificarTodosAtletasUpdate(Treino treino){
        List<Atleta> atletas = atletaRepository.findAll().list();

        for(Atleta atleta : atletas){
            whatsAppResource.sendTextMessage(new MensagemDTO("55"+retirarPrimeiroNove(atleta.getTelefone())+"@c.us", criarMensagemTreinoAtualizando(atleta, treino), "default"));
            System.out.println("Enviado para "+retirarPrimeiroNove(atleta.getTelefone())+"!");
        }
    }

    private String criarMensagemTreinoCadastro(Atleta atleta, Treino treino){
        return "Olá, atleta "+atleta.getNome()+"!\n\nFoi agendado um treino na sua escolinha Handmaxx!\n\nSerá em "
                +formatarData(treino.getData())+", às "+formatarHorario(treino.getHorario())+".\nO local será no(a) "+
                treino.getLocal()+".\n\n*Aguardamos você!*";
    }

    private String criarMensagemTreinoAtualizando(Atleta atleta, Treino treino){
        return "Olá, atleta "+atleta.getNome()+"!\n\nSeu treino foi remarcado!\nAgora, será em "
                +formatarData(treino.getData())+", às "+formatarHorario(treino.getHorario())+".\nO local será no(a) "+
                treino.getLocal()+"\n\n*Aguardamos você!*";
    }


    private String criarMensagemTreinoCancelado(Atleta atleta, Treino treino){
        return "Olá, atleta "+atleta.getNome()+"!\nEstamos cancelado seu treino que estava agendado para "
                +formatarData(treino.getData())+", às "+formatarHorario(treino.getHorario())
                +".\n\n*Pedimos desculpas e contamos com a colaboração de todos.*";
    }


    private String formatarData(LocalDate date){
        if (date==null)
            return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }

    private String formatarHorario(LocalTime time){
        if (time==null)
            return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return time.format(formatter);
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

}
