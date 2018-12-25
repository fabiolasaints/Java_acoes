package com.javaee.fabiola.acoes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.javaee.fabiola.acoes.repositories.MensagemRepository;
import com.javaee.fabiola.acoes.domain.Mensagem;
//import com.javaee.fabiola.acoes.repositories.MensagemRepository;

@Service
public class MensagemServiceImpl implements MensagemService {
	
	@Autowired
	MensagemRepository mensagemRepository;

	@Override
	public Mensagem createNew(Mensagem mensagem) {
		return mensagemRepository.save (mensagem);
	}

}
