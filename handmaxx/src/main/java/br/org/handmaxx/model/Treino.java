    package br.org.handmaxx.model;

    import jakarta.persistence.Entity;
    import lombok.Data;
    import lombok.EqualsAndHashCode;

    import java.time.LocalDate;
    import java.time.LocalTime;

    @Entity
    @Data
    @EqualsAndHashCode(callSuper = true)
    public class Treino extends DefaultEntity {

        private String local;
        // Atualizar esse treino depois com a UML.
        private LocalDate data;
        private LocalTime horario;
    }
