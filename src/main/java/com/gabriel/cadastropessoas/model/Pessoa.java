package com.gabriel.cadastropessoas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Pessoa {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "É necessário fornecer um nome")

    private String nome;
    @NotBlank(message = "É necessário fornecer um CPF")
    @CPF
    private String cpf;
    @NotBlank(message = "É necessário fornecer uma data de nascimento")
    @PastOrPresent(message = "Data de nascimento não pode ser no futuro")
    private LocalDate dataDeNascimento;

    @OneToMany
    @Size(min=1,message = "É necessário fornecer pelo menos um contato")
    private List<Contato> contatos;

    public Pessoa(Long id, String nome, String cpf, LocalDate dataDeNascimento, List<Contato> contatos) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
        this.contatos = contatos;
    }

    public Pessoa() {
    }

    public void validarCpf(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataDeNascimento=" + dataDeNascimento +
                ", contatos=" + contatos +
                '}';
    }
}
