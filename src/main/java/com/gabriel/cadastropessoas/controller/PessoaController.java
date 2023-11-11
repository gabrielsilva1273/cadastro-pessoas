package com.gabriel.cadastropessoas.controller;

import com.gabriel.cadastropessoas.model.Pessoa;
import com.gabriel.cadastropessoas.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "pessoas")
public class PessoaController {
    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }
    @GetMapping
    public Map<String, Object> buscarPorPagina(@RequestParam(defaultValue = "0") Integer numeroDaPagina,
                                               @RequestParam(defaultValue = "4") Integer quantidadePorPagina){
        return pessoaService.buscarPorPagina(numeroDaPagina,quantidadePorPagina);
    }
    @GetMapping(value = "/{id}")
    public Pessoa buscarPorId(@PathVariable Long id){
        return pessoaService.buscarPorId(id);
    }

    @PostMapping
    public Pessoa adicionar(@Valid @RequestBody Pessoa pessoa){
        return pessoaService.salvar(pessoa);
    }

    @PutMapping(value = "/{id}")
    public Pessoa editar(@Valid @RequestBody Pessoa pessoa,@PathVariable Long id){
        return pessoaService.alterar(pessoa,id);
    }

    @DeleteMapping(value = "/{id}")
    public void excluir(@PathVariable Long id){
        pessoaService.excluir(id);
    }
}
