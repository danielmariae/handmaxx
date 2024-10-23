package br.org.handmaxx.repository;

import br.org.handmaxx.model.Mensagem;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MensagemRepository implements PanacheRepository<Mensagem> {
        // Métodos customizados, se necessário
}
