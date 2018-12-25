package com.javaee.fabiola.acoes.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.javaee.fabiola.acoes.domain.Mensagem;

@Repository
public interface MensagemRepository extends MongoRepository<Mensagem, String>{
}
