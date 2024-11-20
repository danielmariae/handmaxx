package br.org.handmaxx.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import br.org.handmaxx.model.Treino;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TreinoRepository implements PanacheRepository<Treino> {
    public Optional<Treino> findByDataHorario(LocalDate data, LocalTime horario) {
        return find("data = ?1 and horario = ?2", data, horario).firstResultOptional();
    }
    
}
