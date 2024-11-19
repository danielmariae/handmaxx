package br.org.handmaxx.service.frequencia;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.org.handmaxx.dto.atleta.AtletaTreinoDTO;
import br.org.handmaxx.dto.frequencia.FrequenciaDTO;
import br.org.handmaxx.dto.frequencia.FrequenciaTreinoDTO;
import br.org.handmaxx.model.Atleta;
import br.org.handmaxx.model.Frequencia;
import br.org.handmaxx.repository.AtletaRepository;
import br.org.handmaxx.repository.FrequenciaRepository;
import br.org.handmaxx.repository.TreinoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class FrequenciaServiceImpl implements FrequenciaService {

    @Inject
    FrequenciaRepository frequenciaRepository;

    @Inject
    AtletaRepository atletaRepository;

    @Inject
    TreinoRepository treinoRepository;

    @Override
    public void registrarPresenca(FrequenciaDTO frequenciaDTO) {
        Frequencia frequencia = new Frequencia();
        frequencia.setAtleta(atletaRepository.findById(frequenciaDTO.atletaId()));
        frequencia.setTreino(treinoRepository.findById(frequenciaDTO.treinoId()));
        frequencia.setPresenca(frequenciaDTO.presenca());
        frequenciaRepository.persist(frequencia);
    }

    @Override
    public List<Frequencia> listarFrequenciasPorAtleta(Long atletaId) {
        return frequenciaRepository.findByAtletaId(atletaId);
    }

    @Override
    public List<Frequencia> listarFrequenciasPorTreino(Long treinoId) {
        return frequenciaRepository.findByTreinoIdOrderByAtletaNome(treinoId);
    }

    @Override
    public List<FrequenciaTreinoDTO> listarAtletasPorTreino(Long treinoId) {
        List<Atleta> atletas = atletaRepository.findAll().list();
        List<Frequencia> frequencias = frequenciaRepository.findByTreinoId(treinoId);

        // Mapeia atletas para DTO, incluindo a presença
        return atletas.stream()
                .map(atleta -> {
                    Optional<Frequencia> frequencia = frequencias.stream()
                            .filter(f -> f.getAtleta().getId().equals(atleta.getId()))
                            .findFirst();

                    boolean presenca = frequencia.map(Frequencia::isPresenca).orElse(false);

                    // Retorna o DTO correspondente
                    return new FrequenciaTreinoDTO(atleta.getId(), atleta.getNome(), presenca);
                })
                // Ordena os atletas alfabeticamente pelo nome
                .sorted(Comparator.comparing(FrequenciaTreinoDTO::nomeAtleta))
                .collect(Collectors.toList());
    }
}
