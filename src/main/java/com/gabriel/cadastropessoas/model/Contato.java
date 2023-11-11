package com.gabriel.cadastropessoas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

@Entity
public class Contato {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "É necessário fornecer um nome")
    private String nome;
    @NotBlank(message = "É necessário fornecer um telefone")
    @Range(min = 102,max = 11,
            message = "Telefone deve conter 10 ou 11 digítos: 2 para DDD, mais 9 para celular " +
                    "ou mais 8 para telefone fixo")
    private Integer telefone;
    @NotBlank(message = "É necessário fornecer um email")
    @Email(message = "Email deve ser válido")
    private String email;

    public Contato(Long id, String nome, Integer telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public Contato() {
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



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
