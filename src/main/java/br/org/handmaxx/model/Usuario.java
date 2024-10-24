package br.org.handmaxx.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Usuario extends DefaultEntity { 
    @Column(unique = true)
    private String email;

    private String login;

    private String senha;
}
