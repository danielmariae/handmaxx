package br.org.handmaxx.dto.mensagem;

import br.org.handmaxx.model.Mensagem;

public record MensagemResponseDTO(
    Long id,
    String mensagem, 
    String text,
    String session,
    String status
) {
    public static MensagemResponseDTO valueOf(Mensagem m){
        return new MensagemResponseDTO(
                                m.getId(),
                                m.getPhone(),
                                m.getText(),
                                m.getApiSession(),
                                m.getStatus());
    }
}
