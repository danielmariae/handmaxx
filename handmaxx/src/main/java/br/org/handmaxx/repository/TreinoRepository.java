package br.org.handmaxx.repository;

import br.org.handmaxx.model.Treino;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TreinoRepository implements PanacheRepository<Treino> {

}
