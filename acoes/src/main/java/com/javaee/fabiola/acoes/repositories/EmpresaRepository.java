package com.javaee.fabiola.acoes.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.javaee.fabiola.acoes.domain.Empresa;

@Repository
public interface EmpresaRepository extends MongoRepository<Empresa, String>{
	List<Empresa> findByName(String nome);
	List<Empresa> findByEmail(String email);
}
