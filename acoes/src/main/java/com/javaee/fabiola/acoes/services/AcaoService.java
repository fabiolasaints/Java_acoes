package com.javaee.fabiola.acoes.services;

import java.util.Set;

import com.javaee.fabiola.acoes.domain.AcaoDemanda;
import com.javaee.fabiola.acoes.domain.AcaoMercado;
import com.javaee.fabiola.acoes.domain.IdMensagem;
import com.javaee.fabiola.acoes.domain.OfertaAcao;
import com.javaee.fabiola.acoes.domain.Acao;


public interface AcaoService {
	Set<Acao> getAll();

	static Acao getById(String id) {
		
		return null;
	}

	Acao createNew(Acao acao);

	Acao emitNew(String empresaId, Acao acao);

	Acao save(String id, Acao acao);
		
	void deleteById(String id);
	
	Set<AcaoDemanda> getAllDemands();

	Set<OfertaAcao> getAllOffers();

	IdMensagem sendMensagem(String fonte, AcaoMercado acaoMercado);

}
