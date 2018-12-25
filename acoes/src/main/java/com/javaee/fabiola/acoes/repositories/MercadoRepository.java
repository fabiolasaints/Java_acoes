package com.javaee.fabiola.acoes.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.javaee.fabiola.acoes.domain.Mercado;

@Repository
public interface MercadoRepository extends MongoRepository<Mercado, String>{
}
