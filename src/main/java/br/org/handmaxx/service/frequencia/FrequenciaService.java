package br.org.handmaxx.service.frequencia;

import java.util.List;

import br.org.handmaxx.dto.atleta.AtletaTreinoDTO;
import br.org.handmaxx.dto.frequencia.FrequenciaDTO;
import br.org.handmaxx.model.Frequencia;

public interface FrequenciaService {
    void registrarPresenca(FrequenciaDTO frequenciaDTO);

    List<Frequencia> listarFrequenciasPorTreino(Long treinoId);

    List<Frequencia> listarFrequenciasPorAtleta(Long atletaId);

    List<AtletaTreinoDTO> listarAtletasPorTreino(Long treinoId);
}
