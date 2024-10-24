package br.org.handmaxx.service.whatsapp;

import br.org.handmaxx.dto.mensagem.MensagemDTO;

public interface WhatsappService {
    String listSessions(boolean all);
    String startSession(String session);
    String stopSession(String session);
    byte[] getQRCode(String session, String format);
    void sendTextMessage(MensagemDTO message);
    void sendSeen(MensagemDTO message);
}   
