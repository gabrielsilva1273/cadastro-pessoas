package com.gabriel.cadastropessoas.repository;

import com.gabriel.cadastropessoas.model.Contato;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends CrudRepository<Contato,Long> {
}
