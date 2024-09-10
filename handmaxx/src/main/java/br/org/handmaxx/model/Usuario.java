package br.org.handmaxx.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Usuario extends DefaultEntity {

    private String login;

    private String senha;

    private String cnpj;
}
