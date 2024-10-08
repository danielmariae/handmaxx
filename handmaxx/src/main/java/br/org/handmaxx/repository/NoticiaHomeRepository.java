package br.org.handmaxx.repository;

import br.org.handmaxx.model.NoticiaHome;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NoticiaHomeRepository implements PanacheRepository<NoticiaHome>{
    
}
