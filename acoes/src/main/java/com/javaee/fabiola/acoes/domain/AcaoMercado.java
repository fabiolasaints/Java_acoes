package com.javaee.fabiola.acoes.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcaoMercado {

	private boolean empresaOferta = false;
	private String investidorId;
	private int quantia;
    private String acaoId;
	private float preco;
    public void setDemanda(AcaoDemanda acaoDemanda) {
		
		
	}
	
}
