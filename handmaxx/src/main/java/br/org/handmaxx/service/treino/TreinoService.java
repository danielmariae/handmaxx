package br.org.handmaxx.service.treino;

import br.org.handmaxx.dto.treino.TreinoDTO;
import br.org.handmaxx.dto.treino.TreinoResponseDTO;

public interface TreinoService {
    public TreinoResponseDTO findById(Long id);
    public TreinoResponseDTO create(TreinoDTO dto);
    public TreinoResponseDTO update(TreinoDTO dto, Long id);
    public void delete(Long id);   
}
