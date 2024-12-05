package br.org.handmaxx.app.error;

import br.org.handmaxx.app.error.global.ErrorResponse;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UniqueConstraintExceptionMapper implements ExceptionMapper<PersistenceException> {

    @Override
    public Response toResponse(PersistenceException exception) {
        // Verificar se a causa da exceção é uma violação de chave única
        Throwable cause = exception.getCause();
        if (cause instanceof org.hibernate.exception.ConstraintViolationException) {
            // Aqui você pode personalizar a mensagem e o status
            ErrorResponse errorResponse = new ErrorResponse(
                    "Conflito de chave única",
                    "Erro ao tentar salvar o registro. A chave deve ser única.",
                    Status.CONFLICT.getStatusCode() // Código HTTP para conflito
            );

            return Response.status(Response.Status.CONFLICT)
                    .entity(errorResponse)
                    .build();
        }
        // Se não for uma violação de chave única, pode-se passar a exceção adiante
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorResponse("Erro interno do servidor", "Ocorreu um erro inesperado: " + exception.getMessage(), Status.INTERNAL_SERVER_ERROR.getStatusCode()))
                .build();
    }
}
