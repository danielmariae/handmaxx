package br.org.handmaxx.service.whatsapp;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.org.handmaxx.dto.mensagem.MensagemDTO;
import br.org.handmaxx.service.whatsapp.waha.WahaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class WhatsappServiceImpl implements WhatsappService {

    @Inject
    @RestClient
    WahaService wahaService;

    @Override
    public String listSessions(boolean all) {
        return wahaService.getSessions(all);
    }

    @Override
    public String startSession(String session) {
        return wahaService.startSession(session);
    }

    @Override
    public String stopSession(String session) {
        // Assumindo que exista um endpoint correspondente para parar uma sessão, seria algo como:
        return wahaService.stopSession(session);  // Precisa ter esse método no WahaService
    }

    @Override
    public byte[] getQRCode(String session, String format) {
        return wahaService.getQRCode(session, format);
    }

    @Override
    public void sendTextMessage(MensagemDTO message) {
        wahaService.sendTextMessage(message);
    }

    @Override
    public void sendSeen(MensagemDTO message) {
        wahaService.sendSeen(message);  // Precisa existir esse método no WahaService
    }
}