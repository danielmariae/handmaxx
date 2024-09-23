package br.org.handmaxx.service.atleta;

import java.util.List;

import br.org.handmaxx.dto.atleta.AtletaDTO;
import br.org.handmaxx.dto.atleta.AtletaResponseDTO;

public interface AtletaService {
    public AtletaResponseDTO findById(Long id);
    public AtletaResponseDTO create(AtletaDTO dto);
    public AtletaResponseDTO update(AtletaDTO dto, Long id);
    public void delete(Long id);
    public List<AtletaResponseDTO> findByNome(String nome);
}
