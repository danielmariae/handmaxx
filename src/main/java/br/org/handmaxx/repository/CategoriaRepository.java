package br.org.handmaxx.repository;

import java.util.List;

import br.org.handmaxx.model.Categoria;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoriaRepository implements PanacheRepository<Categoria> {
    public List<Categoria> findByIds(List<Integer> ids) {
        return list("id IN ?1", ids);
    }

    public Categoria findById(Integer id){
        return find("id = ?1", id).singleResult();
    }
}
