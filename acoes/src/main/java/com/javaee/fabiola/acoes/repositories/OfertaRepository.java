package com.javaee.fabiola.acoes.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.javaee.fabiola.acoes.domain.OfertaAcao;

@Repository
public interface OfertaRepository extends MongoRepository<OfertaAcao, String>{
}
