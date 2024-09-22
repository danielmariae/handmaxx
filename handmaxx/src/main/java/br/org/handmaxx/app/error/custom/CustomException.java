package br.org.handmaxx.app.error.custom;

import br.org.handmaxx.app.error.global.ErrorResponse;

public class CustomException extends RuntimeException {
    private final ErrorResponse errorResponse;

    public CustomException(ErrorResponse errorResponse) {
        super(errorResponse.getMessage()); // Mensagem para a exceção
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
