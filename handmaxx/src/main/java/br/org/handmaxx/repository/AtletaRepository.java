package br.org.handmaxx.repository;

import java.util.List;

import br.org.handmaxx.model.Atleta;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AtletaRepository implements PanacheRepository<Atleta> {

    //Método customizado para buscar atletas pelo nome
    public List<Atleta> findByNome(String nome) {
        return find("nome", nome).list();
    }

    // Exemplo de um método customizado para buscar por CPF
    public Atleta findByCpf(String cpf) {
        return find("cpf", cpf).firstResult();
    }
}
