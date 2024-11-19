package br.org.handmaxx.dto.frequencia;

import br.org.handmaxx.model.Frequencia;

public record FrequenciaTreinoDTO(
    Long atletaId,
    String nomeAtleta,
    boolean frequencia
) {
    public static FrequenciaTreinoDTO valueOf(Frequencia f){
        return new FrequenciaTreinoDTO(f.getAtleta().getId(), f.getAtleta().getNome(), f.isPresenca());
    }
}
