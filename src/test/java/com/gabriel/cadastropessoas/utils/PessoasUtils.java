package com.gabriel.cadastropessoas.utils;

import com.gabriel.cadastropessoas.model.Contato;
import com.gabriel.cadastropessoas.model.Pessoa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PessoasUtils {

    public static Pessoa getPessoaValida() throws Exception {
        Contato contato1 = new Contato();
        //contato1.setId(1L);
        contato1.setNome("André");
        contato1.setEmail("andre@gmail.com");
        contato1.setTelefone("4432633705");
        Contato contato2 = new Contato();
        //contato2.setId(2L);
        contato2.setNome("Maria");
        contato2.setEmail("maria@gmail.com");
        contato2.setTelefone("44999345667");
        List<Contato> contatos = new ArrayList<>();
        contatos.add(contato1);
        contatos.add(contato2);

        Pessoa pessoa = new Pessoa();
        //pessoa.setId(1L);
        pessoa.setNome("Nero");
        pessoa.setDataDeNascimento(LocalDate.parse("2000-12-15"));
        pessoa.setCpf("90957650086");
        pessoa.setContatos(contatos);
        return pessoa;
    }

    public static List<Pessoa> getListaDePessoas(int n) throws Exception {
        List<Pessoa> pessoas = new ArrayList<>();
        for ( int i = 0; i < n; i++ ) {
            Pessoa pessoa = getPessoaValida();
            pessoa.setNome(pessoa.getNome()+ n);
            pessoas.add(pessoa);
        }
        return pessoas;
    }
}
