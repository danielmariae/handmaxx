package br.org.handmaxx.dto.enums;

import br.org.handmaxx.model.NotificacaoAntes;

public record NotificacaoAntesDTO(
    Integer id,
    String descricao
) {
    public static  NotificacaoAntesDTO valueOf(NotificacaoAntes n){
        return new NotificacaoAntesDTO(n.getId(), n.getDescricao());
    }
}
