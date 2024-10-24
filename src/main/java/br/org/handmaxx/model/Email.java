package br.org.handmaxx.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(force=true)
@EqualsAndHashCode(callSuper = true)
public class Email extends DefaultEntity {
    private final String destinatario;
    private final String assunto;
    private final String corpoMensagem;
}
