package br.com.crud.crud.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cadastrados")

public class CrudModel {
    //modelamento dos dados para a tabela
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private long id;

    @Column(name = "nome")
    @Getter @Setter private String nome;
    
    @Column(name = "senha")
    @Getter @Setter private String senha;
    
    @Column(name = "cpf")
    @Getter @Setter private String cpf;
    
    @Column(name = "dataNascimento")
    @Getter @Setter private String dataNascimento;

    public CrudModel(String nome, String senha, String cpf, String dataNascimento) {
    
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public CrudModel() {
    }

    
}
