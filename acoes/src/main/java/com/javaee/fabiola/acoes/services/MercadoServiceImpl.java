package com.javaee.fabiola.acoes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaee.fabiola.acoes.domain.Mercado;
import com.javaee.fabiola.acoes.repositories.MercadoRepository;

@Service
public class MercadoServiceImpl implements MercadoService {
	
	@Autowired
	MercadoRepository mercadoRepository;

	@Override
	public Mercado save(Mercado mercado) {
		mercado.setId(mercado.getId());
		return mercadoRepository.save(mercado);
	}

}
