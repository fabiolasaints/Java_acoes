package com.javaee.fabiola.acoes.services;

import java.util.Set;

import com.javaee.fabiola.acoes.domain.Acao;
import com.javaee.fabiola.acoes.domain.AcaoDemanda;
import com.javaee.fabiola.acoes.domain.AcaoMercado;


public interface DemandaService {

	static AcaoDemanda createNew(AcaoMercado acaoMercado) {
		
		return null;
	}
	
	Set<AcaoDemanda> getAll();

	Set<AcaoDemanda> getAllByAcao(Acao acao);

	AcaoDemanda save(AcaoDemanda demanda);

	AcaoDemanda createNew1(AcaoMercado acaoMercado);

	

}
