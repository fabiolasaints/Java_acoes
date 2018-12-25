package com.javaee.fabiola.acoes.services;

import com.javaee.fabiola.acoes.domain.AcaoMercado;
import com.javaee.fabiola.acoes.domain.OfertaAcao;

public interface OfertaService {

	OfertaAcao createNew(AcaoMercado acaoMercado);
	
	OfertaAcao save(OfertaAcao oferta);
	
}
