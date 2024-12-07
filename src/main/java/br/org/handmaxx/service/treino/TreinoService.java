package br.org.handmaxx.service.treino;

import java.util.List;

import br.org.handmaxx.dto.treino.TreinoCreateDTO;
import br.org.handmaxx.dto.treino.TreinoDTO;
import br.org.handmaxx.dto.treino.TreinoFullResponseDTO;
import br.org.handmaxx.dto.treino.TreinoResponseDTO;

public interface TreinoService {
    public TreinoFullResponseDTO create(TreinoCreateDTO dto);
    public TreinoFullResponseDTO update(TreinoDTO dto, Long id);
    public void delete(Long id);   
    public TreinoFullResponseDTO findById(Long id);
    public List<TreinoResponseDTO> findAll();
    public List<TreinoResponseDTO> findProximosTresTreinos();
}
