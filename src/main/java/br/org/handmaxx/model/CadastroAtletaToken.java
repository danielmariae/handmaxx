package br.org.handmaxx.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class CadastroAtletaToken extends DefaultEntity {
    private String token;
    private LocalDateTime dataExpiracao;
    private boolean utilizado;

    @ManyToOne
    @JoinColumn(name = "atleta_id")
    private Atleta atleta;

    public boolean isExpirado() {
        return LocalDateTime.now().isAfter(dataExpiracao);
    }
}
