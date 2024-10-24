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

    public List<Atleta> findAtletasByTreinoId(Long treinoId) {
        return list("SELECT a FROM Atleta a JOIN Frequencia f ON a.id = f.atleta.id WHERE f.treino.id = ?1", treinoId);
    }   
}
