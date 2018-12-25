package com.javaee.fabiola.acoes.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaee.fabiola.acoes.domain.Investidor;
import com.javaee.fabiola.acoes.repositories.InvestidorRepository;

@Service
public class InvestidorServiceImpl implements InvestidorService {

	@Autowired
	private InvestidorRepository investidorRepository;

	@Override
	public Set<Investidor> getAll() {
		Set<Investidor> investidores = new HashSet<>();
		investidorRepository.findAll().iterator().forEachRemaining(investidores::add);
		return investidores;
	}

	@Override
	public Investidor getById(String id) {
		return getInvestidorById(id);
	}

	private Investidor getInvestidorById(String id) {
		Optional<Investidor> investidorOptional = investidorRepository.findById(id);

		if (!investidorOptional.isPresent()) {
			throw new IllegalArgumentException("Investidor não encontradp pelo ID value: " + id.toString());
		}

		return investidorOptional.get();
	}
	
	@Override
	public Investidor createNew(Investidor investidor) {
		Investidor investidorInd0;
		try {
			investidorInd0 = investidorRepository.findByName(investidor.getNome()).get(0);
		} catch (Exception e) {
			return investidorRepository.save(investidor);
		}
		throw new IllegalArgumentException("Investidor já existe com o ID: " + investidorInd0.getId());
	}

	@Override
	public Investidor save(String id, Investidor investidor) {
		investidor.setId(id);
		return investidorRepository.save(investidor);
	}

	@Override
	public void deleteById(String id) {
		investidorRepository.deleteById(id);
	}

}
