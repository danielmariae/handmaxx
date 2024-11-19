package br.org.handmaxx.service.frequencia;

import java.util.List;

import br.org.handmaxx.dto.frequencia.FrequenciaDTO;
import br.org.handmaxx.dto.frequencia.FrequenciaResponseDTO;
import br.org.handmaxx.dto.frequencia.FrequenciaTreinoDTO;
import br.org.handmaxx.model.Frequencia;

public interface FrequenciaService {
    void registrarPresenca(FrequenciaDTO frequenciaDTO);

    List<FrequenciaResponseDTO> listarFrequenciasPorTreino(Long treinoId);

    List<Frequencia> listarFrequenciasPorAtleta(Long atletaId);

    List<FrequenciaTreinoDTO> listarAtletasPorTreino(Long treinoId);
}
