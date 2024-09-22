package br.org.handmaxx.app.error.global;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorResponse {
    private LocalDateTime timestamp;
    private String message;
    private String details;
    private int status;

    public ErrorResponse(String message, String details, int status) {
        this.timestamp = LocalDateTime.now();  // Define o timestamp no momento da criação
        this.message = message;
        this.details = details;
        this.status = status;
    }
}
