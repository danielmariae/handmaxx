package br.org.handmaxx.service.treino;

import br.org.handmaxx.app.error.custom.CustomException;
import br.org.handmaxx.app.error.global.ErrorResponse;
import br.org.handmaxx.dto.treino.TreinoDTO;
import br.org.handmaxx.dto.treino.TreinoResponseDTO;
import br.org.handmaxx.model.Treino;
import br.org.handmaxx.repository.TreinoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;

@ApplicationScoped
public class TreinoServiceImpl implements TreinoService {

    @Inject
    TreinoRepository treinoRepository;

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

        return TreinoResponseDTO.valueOf(treino);
    }
    
}
