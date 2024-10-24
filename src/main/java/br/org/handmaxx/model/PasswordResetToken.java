package br.org.handmaxx.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class PasswordResetToken extends DefaultEntity {
    private String token;
    @ManyToOne
    private Usuario usuario;
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;
}
