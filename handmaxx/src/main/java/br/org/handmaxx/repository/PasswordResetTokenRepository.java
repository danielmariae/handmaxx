package br.org.handmaxx.repository;

import br.org.handmaxx.model.PasswordResetToken;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PasswordResetTokenRepository implements PanacheRepository<PasswordResetToken> {
    public PasswordResetToken findByToken(String token){
        return find("token", token).firstResult();
    }
}
