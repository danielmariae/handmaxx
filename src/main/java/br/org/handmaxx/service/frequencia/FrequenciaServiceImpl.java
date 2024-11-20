package br.org.handmaxx.service.frequencia;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.org.handmaxx.app.error.custom.CustomException;
import br.org.handmaxx.app.error.global.ErrorResponse;
import br.org.handmaxx.dto.frequencia.FrequenciaDTO;
import br.org.handmaxx.dto.frequencia.FrequenciaResponseDTO;
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
    public void registrarVariasFrequencias(List<FrequenciaDTO> frequenciasDTO) {
        List<Frequencia> frequencias = frequenciasDTO.stream()
            .map(dto -> {
                Frequencia frequencia = new Frequencia();
                frequencia.setAtleta(atletaRepository.findById(dto.atletaId()));
                frequencia.setTreino(treinoRepository.findById(dto.treinoId()));
                frequencia.setPresenca(dto.presenca());
    
                // Remover qualquer frequência já existente com o mesmo treino e atleta
                frequenciaRepository.deletarPorTreinoEAtleta(dto.treinoId(), dto.atletaId());
    
                return frequencia;
            })
            .toList();
    
        // Persistir todas as frequências em uma única transação
        frequenciaRepository.persist(frequencias);
    }
    

    @Override
    public List<Frequencia> listarFrequenciasPorAtleta(Long atletaId) {
        return frequenciaRepository.findByAtletaId(atletaId);
    }

    @Override
    public List<FrequenciaResponseDTO> listarFrequenciasPorTreino(Long treinoId) {
        List<Frequencia> frequencias = frequenciaRepository.findByTreinoIdOrderByAtletaNome(treinoId);
        if (frequencias == null) {
            throw new CustomException(
                    new ErrorResponse("Nenhuma frequencia encontrada", "FrequenciaServiceImpl(listarFrequenciasPorTreino)", 404));
        }
        return frequencias.stream()
                .map(FrequenciaResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<FrequenciaTreinoDTO> listarAtletasPorTreino(Long treinoId) {
        List<Atleta> atletas = atletaRepository.findAtletasByTreinoId(treinoId);
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
