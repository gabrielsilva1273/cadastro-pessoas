package com.gabriel.cadastropessoas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;


@Entity
public class Contato {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "É necessário fornecer um nome para o contato")
    private String nome;

    @NotBlank(message = "É necessário fornecer um email para o contato")
    @Email(message = "Email deve ser válido")
    private String email;

    @NotBlank(message = "É necessário fornecer um número de telefone")
    @NotNull(message = "É necessário fornecer um número de telefone")
    @Length(min = 10,max = 11,message = "10 ou 11 digitos no campo telefone, com DDD")
    private String telefone;

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
