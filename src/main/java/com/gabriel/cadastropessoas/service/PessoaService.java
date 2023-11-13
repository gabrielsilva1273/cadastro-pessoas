package com.gabriel.cadastropessoas.service;

import com.gabriel.cadastropessoas.model.Pessoa;
import com.gabriel.cadastropessoas.repository.PessoaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa salvar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa alterar(Pessoa pessoa,Long id) {
        pessoa.setId(id);
        salvar(pessoa);
        return buscarPorId(id);
    }

    public void excluir(Long id) {
        if(pessoaRepository.existsById(id)){
            pessoaRepository.deleteById(id);
        }
    }

    public Pessoa buscarPorId(Long id) {
        return pessoaRepository.findById(id).orElseThrow();
    }

    public Map<String, Object> buscarPorPagina(Integer numeroDaPagina, Integer quantidadePorPagina){
        numeroDaPagina = numeroDaPagina -1;
        Pageable pageable = PageRequest.of(numeroDaPagina,quantidadePorPagina);
        Page<Pessoa> paginaPessoa = pessoaRepository.findAll(pageable);
        Map<String,Object> resposta = new HashMap<>();
        resposta.put("pessoas",paginaPessoa.getContent());
        resposta.put("paginaAtual",paginaPessoa.getNumber()+1);
        resposta.put("totalDePessoas",paginaPessoa.getTotalElements());
        resposta.put("totalDePaginas",
                Math.ceil((double) paginaPessoa.getTotalElements() /quantidadePorPagina
        ));

        return resposta;
    }
}
