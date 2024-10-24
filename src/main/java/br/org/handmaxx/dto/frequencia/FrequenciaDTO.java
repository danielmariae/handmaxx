package br.org.handmaxx.dto.frequencia;

import br.org.handmaxx.model.Frequencia;

public record FrequenciaDTO(
    Long atletaId,
    Long treinoId,
    boolean presenca
) {
    public static FrequenciaDTO valueOf(Frequencia f){
        return new FrequenciaDTO(
            f.getAtleta().getId(),
            f.getTreino().getId(),
            f.isPresenca()
        );
    }
}
