package br.org.handmaxx.app.error.custom;

import br.org.handmaxx.app.error.global.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Provider
public class CustomExceptionMapper implements ExceptionMapper<CustomException> {

    @Override
    public Response toResponse(CustomException exception) {
        log.error("Erro capturado: ", exception);

        ErrorResponse errorResponse = exception.getErrorResponse();  // Obtendo o ErrorResponse

        return Response.status(errorResponse.getStatus())
                .entity(errorResponse)
                .build();
    }
}

