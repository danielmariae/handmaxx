package br.org.handmaxx.dto.mensagem;

import br.org.handmaxx.model.Mensagem;

public record MensagemDTO(
    String chatId, 
    String text,
    String session
) {
    public static MensagemDTO valueOf(Mensagem m){
        return new MensagemDTO(m.getPhone(),
                               m.getText(),
                               m.getApiSession());
    }
}
