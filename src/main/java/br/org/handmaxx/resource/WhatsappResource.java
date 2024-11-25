package br.org.handmaxx.resource;

import br.org.handmaxx.dto.mensagem.MensagemDTO;
import br.org.handmaxx.service.whatsapp.WhatsappService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/whatsapp")
@Authenticated
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WhatsappResource {

    @Inject
    WhatsappService whatsappService;

    @GET
    @Path("/sessions")
    public Response listSessions(@QueryParam("all") boolean all) {
        return Response.ok(whatsappService.listSessions(all)).build();
    }

    @POST
    @Path("/sessions/{session}/start")
    public Response startSession(@PathParam("session") String session) {
        return Response.status(Response.Status.CREATED)
                .entity(whatsappService.startSession(session))
                .build();
    }

    @POST
    @Path("/sessions/{session}/stop")
    public Response stopSession(@PathParam("session") String session) {
        return Response.status(Response.Status.CREATED)
                .entity(whatsappService.stopSession(session))
                .build();
    }

    @GET
    @Path("/{session}/auth/qr")
    @Produces({"image/png", "application/json"})
    public Response getQRCode(@PathParam("session") String session, @QueryParam("format") String format) {
        byte[] qrCode = whatsappService.getQRCode(session, format);
        return Response.ok(qrCode).build();
    }

    @POST
    @Path("/sendText")
    public Response sendTextMessage(MensagemDTO message) {
        whatsappService.sendTextMessage(message);
        return Response.status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/sendSeen")
    public Response sendSeen(MensagemDTO message) {
        whatsappService.sendSeen(message);
        return Response.status(Response.Status.CREATED).build();
    }
}