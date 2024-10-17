package br.org.handmaxx.service.messaging;

import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.Mail;

import jakarta.inject.Inject;

@ApplicationScoped
public class EmailServiceImpl implements EmailService {

    @Inject
    Mailer mailer;

    @Override
    public void sendEmail(String to, String subject, String text) {
        mailer.send(Mail.withText(to, subject, text));
    }
}
