package br.org.handmaxx.repository;

import br.org.handmaxx.model.PasswordResetToken;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PasswordResetTokenRepository implements PanacheRepository<PasswordResetToken> {
    public PasswordResetToken findByToken(String token){
        return find("token", token).singleResult();
    }
    public PasswordResetToken findByUserId(Long id){
        return find("SELECT p FROM PasswordResetToken p WHERE p.usuario.id = ?1", id).firstResult();
    }
}
