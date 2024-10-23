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
import br.org.handmaxx.service.atleta.AtletaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import br.org.handmaxx.service.whatsapp.WhatsappService;
import jakarta.persistence.PersistenceException;

@ApplicationScoped
public class TreinoServiceImpl implements TreinoService {

    @Inject
    TreinoRepository treinoRepository;

    @Inject 
    AtletaRepository atletaRepository;

    @Inject
    WhatsappService whatsappService;
    
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


        return TreinoResponseDTO.valueOf(treino);
    }

    @Override
    public void delete(Long id) {
        Treino treino = treinoRepository.findById(id);
        if(treino == null){
            throw new CustomException(new ErrorResponse("Treino não encontrado", "TreinoServiceImpl(delete)", 404));
        }
        try {
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
            throw new CustomException(new ErrorResponse("Atleta não encontrado", "AtletaServiceImpl(update)", 404));
        }

        treino.setLocal(dto.local());
        treino.setData(dto.data());
        treino.setHorario(dto.horario());

        notificarTodosAtletas(dto);
        
        return TreinoResponseDTO.valueOf(treino);
    }    

    private void notificarTodosAtletas(TreinoDTO dto){
        List<Atleta> atletas = atletaRepository.findAll().list();
        
        for(Atleta atleta : atletas){
            whatsappService.sendTextMessage(new MensagemDTO("55"+atleta.getTelefone()+"@c.us", criarMensagemTreino(atleta, dto), "default"));
        }
    }

    private String criarMensagemTreino(Atleta atleta, TreinoDTO dto){
        return "Olá, atleta "+atleta.getNome()
                +"!\nFoi agendado um treino na sua escolinha Handmaxx!\nSerá "
                +formatarData(dto.data())+"às "+dto.horario()+".\nO local será"+
                dto.local()+"\n\n*Aguardamos você!*";
    }

    private String formatarData(LocalDate date){
        if (date==null)
            return null;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return date.format(formatter);
    }

}
