package com.javaee.fabiola.acoes.services;

import java.util.Set;

import com.javaee.fabiola.acoes.domain.Investidor;
//import com.javaee.fabiola.acoes.domain.Investidor;

public interface InvestidorService {
	Set<Investidor> getAll();

	Investidor getById(String id);

	Investidor createNew(Investidor buyer);

	Investidor save(String id, Investidor buyer);

	void deleteById(String id);
}
