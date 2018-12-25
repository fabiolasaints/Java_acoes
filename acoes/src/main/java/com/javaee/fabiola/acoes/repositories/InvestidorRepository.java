package com.javaee.fabiola.acoes.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.javaee.fabiola.acoes.domain.Investidor;

@Repository
public interface InvestidorRepository extends  MongoRepository<Investidor, String>{
	List<Investidor> findByName(String nome);
	List<Investidor> findByEmail(String email);
}
