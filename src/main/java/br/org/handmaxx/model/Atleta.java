package br.org.handmaxx.model;

import java.time.LocalDate;
import java.time.Period;

//import org.jboss.logmanager.handlers.PeriodicRotatingFileHandler.Period;

//import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Atleta extends DefaultEntity {

    private String nome;

    // Remover CPF do cadastro inicial e incluir no update
    @Column(unique = true)
    private String cpf;

    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    
    // Telefone será obrigatório no cadastro inicial
    private String telefone;

    @OneToOne// Removido CascadeType.PERSIST para permitir que endereco seja nulo no cadastro inicial
    private Endereco endereco;
    
    @OneToOne// Removido CascadeType.PERSIST para permitir que dados sociais seja nulo no cadastro inicial
    private QuestionarioSocial dadosSociais;

    @Enumerated(EnumType.ORDINAL)
    private Categoria categoria;

    private boolean cadastroCompleto = false;

    // Método para atualizar automaticamente a categoria com base na data de nascimento
    public void atualizarCategoria() {
        if (this.dataNascimento != null) {
            int idade = Period.between(this.dataNascimento, LocalDate.now()).getYears();
            this.categoria = Categoria.getCategoriaPorIdade(idade);
        }
    }

    // Chamando o método sempre que o cadastro for salvo ou atualizado
    @PrePersist
    @PreUpdate
    public void calcularCategoriaAntesDeSalvar() {
        atualizarCategoria();
    }
}
