package br.org.handmaxx.repository;


import br.org.handmaxx.model.Publicacao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PublicacaoRepository implements PanacheRepository<Publicacao>{
    
}
