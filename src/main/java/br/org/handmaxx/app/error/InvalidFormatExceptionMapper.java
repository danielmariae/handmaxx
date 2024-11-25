package br.org.handmaxx.app.error;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import br.org.handmaxx.app.error.global.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class InvalidFormatExceptionMapper implements ExceptionMapper<InvalidFormatException>{

    @Override
    public Response toResponse(InvalidFormatException exception) {
        // Obtém o nome do campo e o valor inválido
        String fieldName = exception.getPath().stream()
                .map(reference -> reference.getFieldName())
                .reduce((first, second) -> second) // Obtém o último campo do path
                .orElse("unknown");
        String invalidValue = exception.getValue().toString();

        // Mensagem descritiva do erro
        String errorMessage = String.format("O valor '%s' é inválido para o campo '%s'.", invalidValue, fieldName);

        // Cria a resposta de erro
        ErrorResponse errorResponse = new ErrorResponse(
                "Erro de formatação ao processar os dados enviados.",
                errorMessage,
                Response.Status.BAD_REQUEST.getStatusCode()
        );

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .build();
    }

}
