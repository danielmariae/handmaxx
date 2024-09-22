package br.org.handmaxx.app.error.global;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Slf4j  // Lombok para facilitar o uso de logs
@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        log.error("Erro capturado: ", exception);  // Uso do Lombok para logging

        ErrorResponse errorResponse = new ErrorResponse(
                exception.getMessage(),
                "Ocorreu um erro inesperado",
                Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()
        );

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorResponse)
                .build();
    }
}

// import jakarta.enterprise.context.ApplicationScoped;
// import jakarta.ws.rs.core.Response;
// import jakarta.ws.rs.core.Response.Status;
// import jakarta.ws.rs.ext.ExceptionMapper;
// import jakarta.ws.rs.ext.Provider;

// @Provider
// @ApplicationScoped
// public class GlobalExceptionMapper implements ExceptionMapper<Excep> {
//     @Override
//     public Response toResponse(GeneralErrorException exception) {
//         GeneralError ge = new GeneralError(exception.getCode(), exception.getCodeMessage());
//         ge.setSubjectError(exception.getSubjectName(), exception.getMessage());
//         return Response.status(Status.BAD_REQUEST).entity(ge).build();
//     }
    
// }