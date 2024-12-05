package br.org.handmaxx.repository;

import java.util.List;
import java.util.Optional;

import br.org.handmaxx.model.Atleta;
import br.org.handmaxx.model.Categoria;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

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
        return list("SELECT a FROM Treino t JOIN t.listaAtletas a WHERE t.id = ?1", treinoId);
    }    

    public List<Atleta> findByIds(List<Long> ids) {
        return list("id IN ?1", ids);
    }

    public Atleta findAtletaByToken(String token){
        return find("SELECT a from CadastroAtletaToken t JOIN t.atleta a WHERE t.token = ?1", token).singleResult();
    }
    
    public Optional<List<Atleta>> findByCategoria(Categoria categoria) {
        return Optional.ofNullable(list("categoria", categoria));
    }}
