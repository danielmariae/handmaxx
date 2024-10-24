package br.org.handmaxx.dto.email;
import br.org.handmaxx.model.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailDTO(
    @NotBlank String destinatario,
    @NotBlank String assunto,
    @NotBlank String corpoMensagem
) {
    public static EmailDTO valueOf(Email email){
        return new EmailDTO(email.getDestinatario(), 
                            email.getAssunto(),
                            email.getCorpoMensagem());
    }
}

