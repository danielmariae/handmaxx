package br.org.handmaxx.app.error.validation;

import br.org.handmaxx.app.error.custom.CustomException;
import br.org.handmaxx.app.error.global.ErrorResponse;

public class ValidationException extends CustomException {
    public ValidationException(ErrorResponse errorResponse) {
        super(errorResponse);
    }
}
