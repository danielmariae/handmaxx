package br.org.handmaxx.service.email;

import br.org.handmaxx.dto.email.EmailDTO;
import br.org.handmaxx.dto.email.EmailResponseDTO;
import br.org.handmaxx.model.Email;
import br.org.handmaxx.repository.EmailRepository;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class EmailServiceImpl implements EmailService {

    @Inject
    Mailer mailer;

    @Inject
    EmailRepository emailRepository;

    @Override
    public void enviarEmail(EmailDTO emailDTO) {
        try {
            mailer.send(Mail.withHtml(emailDTO.destinatario(), emailDTO.assunto(), emailDTO.corpoMensagem())
            .addHeader("Content-Type", "text/html; charset=UTF-8"));
        log.info("Email enviado para {}", emailDTO.destinatario());
        } catch (Exception e) {
            log.error("Erro ao enviar o email: {}", e.getMessage());
            throw new RuntimeException("Falha ao enviar email", e);
        }
    }

    @Override
    public EmailResponseDTO salvarEmail(EmailDTO emailDTO) {
        Email email = new Email(emailDTO.destinatario(), emailDTO.assunto(), emailDTO.corpoMensagem());
        try {
            emailRepository.persist(email);
            log.info("Email salvo no banco de dados com ID {}", email.getId());
        } catch (Exception e) {
            log.error("Erro ao salvar email no banco de dados: {}", e.getMessage());
            throw new RuntimeException("Falha ao salvar email no banco de dados", e);
        }
        return new EmailResponseDTO(email.getId(), email.getDestinatario(), email.getAssunto(), email.getCorpoMensagem());
    }
}
