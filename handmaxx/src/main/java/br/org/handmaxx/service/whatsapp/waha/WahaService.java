package br.org.handmaxx.service.whatsapp.waha;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import br.org.handmaxx.dto.mensagem.MensagemDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@RegisterRestClient(baseUri="http://localhost:3000")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface WahaService {    
    @GET
    @Path("/api/sessions")
    String getSessions(@QueryParam("all") boolean all);

    @POST
    @Path("/api/sessions/{session}/start")
    String startSession(@PathParam("session") String session);

    @POST
    @Path("/api/sessions/{session}/stop")
    String stopSession(@PathParam("session") String session);  // Assumindo que esse endpoint exista

    @GET
    @Path("/api/{session}/auth/qr")
    @Produces({"image/png", "application/json"})
    byte[] getQRCode(@PathParam("session") String session, @QueryParam("format") String format);

    @POST
    @Path("/api/sendText")
    void sendTextMessage(MensagemDTO message);

    @POST
    @Path("/api/sendSeen")
    void sendSeen(MensagemDTO message);
}
