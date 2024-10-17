package br.org.handmaxx.dto.frequencia;

import br.org.handmaxx.model.Frequencia;

public record FrequenciaResponseDTO(
    String nomeAtleta,
    String localTreino,
    boolean presenca
) {
    public static FrequenciaResponseDTO valueOf(Frequencia f){
        return new FrequenciaResponseDTO(
            f.getAtleta().getNome(),
            f.getTreino().getLocal(),
            f.isPresenca()
        );
    }
}
