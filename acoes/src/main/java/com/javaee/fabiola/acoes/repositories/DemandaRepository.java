package com.javaee.fabiola.acoes.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.javaee.fabiola.acoes.domain.Acao;
import com.javaee.fabiola.acoes.domain.AcaoDemanda;

@Repository
public interface DemandaRepository extends MongoRepository<AcaoDemanda, String>{
	List<DemandaRepository> findByAcao(Acao acao);
}
