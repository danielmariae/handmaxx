package br.org.handmaxx.service.treino;

import br.org.handmaxx.app.error.custom.CustomException;
import br.org.handmaxx.app.error.global.ErrorResponse;
import br.org.handmaxx.dto.mensagem.MensagemDTO;
import br.org.handmaxx.dto.treino.TreinoDTO;
import br.org.handmaxx.dto.treino.TreinoResponseDTO;
import br.org.handmaxx.model.Atleta;
import br.org.handmaxx.model.Treino;
import br.org.handmaxx.repository.AtletaRepository;
import br.org.handmaxx.repository.TreinoRepository;
import br.org.handmaxx.resource.WhatsappResource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import jakarta.persistence.PersistenceException;

@ApplicationScoped
public class TreinoServiceImpl implements TreinoService {

    @Inject
    TreinoRepository treinoRepository;

    @Inject 
    AtletaRepository atletaRepository;

    @Inject
    WhatsappResource whatsAppResource;
    
    @Override
    public TreinoResponseDTO create(TreinoDTO dto) {
        Treino treino = new Treino();
        
        treino.setLocal(dto.local());
        treino.setData(dto.data());
        treino.setHorario(dto.horario());
        
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

        return TreinoResponseDTO.valueOf(treino);
    }

    @Override
    public void delete(Long id) {
        Treino treino = treinoRepository.findById(id);

        if(treino == null){
            throw new CustomException(new ErrorResponse("Treino não encontrado", "TreinoServiceImpl(delete)", 404));
        }
        try {
            notificarTodosAtletasDelete(treino);
            treinoRepository.delete(treino);
        } catch (Exception e) {
            throw new CustomException(new ErrorResponse("Erro no servidor.", "TreinoServiceImpl(delete): "+e.getMessage(), 500));
        }
    }

    @Override
    public TreinoResponseDTO findById(Long id) {
        Treino treino = treinoRepository.findById(id);
        if(treino == null){
            throw new CustomException(new ErrorResponse("Treino não encontrado", "TreinoServiceImpl(findById)", 404));
        }
        return TreinoResponseDTO.valueOf(treino);
    }

    @Override
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
        
        notificarTodosAtletasUpdate(treino);

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
