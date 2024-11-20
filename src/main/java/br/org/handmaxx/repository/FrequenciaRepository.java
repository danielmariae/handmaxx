package br.org.handmaxx.repository;

import java.util.List;

import br.org.handmaxx.model.Frequencia;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FrequenciaRepository implements PanacheRepository<Frequencia>{
    
    public List<Frequencia> findByAtletaId(Long atletaId) {
        return find("atleta.id", atletaId).list();
    }

    public List<Frequencia> findByTreinoId(Long treinoId) {
        return find("treino.id", treinoId).list();
    }

    public List<Frequencia> findByTreinoIdOrderByAtletaNome(Long treinoId) {
        return find("treino.id = ?1 ORDER BY atleta.nome", treinoId).list();
    }

    public void deletarPorTreinoEAtleta(Long treinoId, Long atletaId) {
        delete("treino.id = ?1 and atleta.id = ?2", treinoId, atletaId);
    }    
}
