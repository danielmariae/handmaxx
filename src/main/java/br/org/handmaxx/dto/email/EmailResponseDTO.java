package br.org.handmaxx.dto.email;

import br.org.handmaxx.model.Email;

public record EmailResponseDTO(
    Long id,
    String destinatario,
    String assunto,
    String corpoMensagem
) {
    public static EmailResponseDTO valueOf(Email email){
        return new EmailResponseDTO(email.getId(), email.getDestinatario(), email.getAssunto(), email.getCorpoMensagem());
    }
}
