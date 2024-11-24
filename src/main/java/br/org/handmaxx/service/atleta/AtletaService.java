package br.org.handmaxx.service.atleta;

import java.util.List;

import br.org.handmaxx.dto.atleta.AtletaCadastroInicialDTO;
import br.org.handmaxx.dto.atleta.AtletaDTO;
import br.org.handmaxx.dto.atleta.AtletaResponseDTO;
import br.org.handmaxx.dto.atleta.AtletaTreinoDTO;

public interface AtletaService {
    public AtletaResponseDTO findById(Long id);
    public AtletaResponseDTO create(AtletaDTO dto);
    public AtletaResponseDTO createInitial(AtletaCadastroInicialDTO dto);
    public AtletaResponseDTO update(AtletaDTO dto, Long id);
    public void delete(Long id);
    public List<AtletaResponseDTO> findByNome(String nome);
    public List<AtletaResponseDTO> findAll();
    public List<AtletaTreinoDTO> findAllTreinos();
    public void gerarTokenCadastro(Long id);
    public boolean validarToken(String token);
}
