package br.org.handmaxx.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import br.org.handmaxx.model.Treino;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TreinoRepository implements PanacheRepository<Treino> {

    public Optional<Treino> findByDataHorario(LocalDateTime dataHorario) {
        return find("dataHorario = ?1", dataHorario).firstResultOptional();
    }

    // Método para encontrar treinos futuros
    public List<Treino> findTreinosFuturos() {
        LocalDateTime now = LocalDateTime.now();
        return list("dataHorario > ?1", now);
    }

    // Método para encontrar os próximos 3 treinos
    public List<Treino> findProximosTresTreinos() {
        LocalDateTime now = LocalDateTime.now();
        return find("dataHorario > ?1 ORDER BY dataHorario ASC", now).page(0, 3).list();
    }
}
