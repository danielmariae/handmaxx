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
@EqualsAndHashCode(callSuper=true)
public class Mensagem extends DefaultEntity {
    private final String phone;
    private final String text;
    private final String apiSession;
    private final String status;
}
