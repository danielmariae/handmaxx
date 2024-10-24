package br.org.handmaxx.repository;

import br.org.handmaxx.model.Email;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmailRepository implements PanacheRepository<Email>{

}
