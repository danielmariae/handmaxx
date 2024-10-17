package br.org.handmaxx.service.messaging;

public interface EmailService {
    void sendEmail(String to, String subject, String text);
}
