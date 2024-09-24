    package br.org.handmaxx.model;

    import jakarta.persistence.Entity;
    import lombok.Data;
    import lombok.EqualsAndHashCode;

    import java.time.LocalDate;

    @Entity
    @Data
    @EqualsAndHashCode(callSuper = true)
    public class Treino extends DefaultEntity {

        private String local;
        private LocalDate horario;

    }
