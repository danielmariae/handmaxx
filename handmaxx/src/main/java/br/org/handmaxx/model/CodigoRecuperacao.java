package br.org.handmaxx.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CodigoRecuperacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private String codigoVerificacao;

    @Column(nullable = false)
    private LocalDateTime expiration;

    @Column(nullable = false)
    private Long userId;

    private boolean verificado = false;
}