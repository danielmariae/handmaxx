package br.org.handmaxx.app.error.validation;

import java.util.stream.Collectors;

import br.org.handmaxx.app.error.global.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;


@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        // Coleta e concatena todas as mensagens de erro em uma única string
        String allErrors = exception.getConstraintViolations()
            .stream()
            .map(this::formatViolation)
            .collect(Collectors.joining(", "));

        ErrorResponse errorResponse = new ErrorResponse(allErrors, "Validation", 400);

            
        // Retorna a resposta com status 400 e a mensagem de erro consolidada
        return Response.status(Response.Status.BAD_REQUEST)
                       .entity(errorResponse)
                       .build();
    }

    // Método auxiliar para formatar as violações no formato 'campo: mensagem'
    private String formatViolation(ConstraintViolation<?> violation) {
        return violation.getPropertyPath() + ": " + violation.getMessage();
    }
}

