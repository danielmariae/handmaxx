package br.org.handmaxx.service.email;

import br.org.handmaxx.dto.email.EmailDTO;
import br.org.handmaxx.dto.email.EmailResponseDTO;

public interface EmailService {
    public void enviarEmail(EmailDTO emailDTO);
    public EmailResponseDTO salvarEmail(EmailDTO emailDTO);
}
