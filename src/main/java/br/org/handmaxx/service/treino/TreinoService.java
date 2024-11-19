package br.org.handmaxx.service.treino;

import br.org.handmaxx.dto.treino.TreinoCreateDTO;
import br.org.handmaxx.dto.treino.TreinoDTO;
import br.org.handmaxx.dto.treino.TreinoResponseDTO;

public interface TreinoService {
    public TreinoResponseDTO findById(Long id);
    public TreinoResponseDTO create(TreinoCreateDTO dto);
    public TreinoResponseDTO update(TreinoDTO dto, Long id);
    public void delete(Long id);   
}
