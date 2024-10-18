package br.org.handmaxx.repository;

import java.util.Optional;

import br.org.handmaxx.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {
    
    public Usuario findByLogin(String login) {
        try {
            return find("login = ?1", login).singleResult();
        } catch (NoResultException e) {
            System.out.println("Usuário não encontrado com login: " + login);
            return null;
        }
    }

    public Usuario findByEmail(String email) {
        try {
            return find("email = ?1", email).singleResult();
        } catch (NoResultException e) {
            System.out.println("Usuário não encontrado com email: " + email);
            return null;
        }
    }

    public Optional<Usuario> findByEmailOptional(String email) {
        return find("email", email).firstResultOptional();
    }

    public Usuario findByLoginAndSenha(String login, String senha) {
        try {
            return find("login = ?1 and senha = ?2", login, senha).singleResult();
        } catch (NoResultException e) {
            System.out.println("Usuário não encontrado com login e senha fornecidos.");
            return null;
        }
    }

}
