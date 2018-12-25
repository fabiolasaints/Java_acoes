package com.javaee.fabiola.acoes.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcaoDemandaRest {
	
	public AcaoDemandaRest(AcaoDemanda acaoDemanda) {
		this.setId(acaoDemanda.getId());
		this.setQuantia(acaoDemanda.getQuantia());
		this.setQuantiaComprada(acaoDemanda.getQuantiaComprada());
		this.setPreco(acaoDemanda.getPreco());
		this.setTimestamp(acaoDemanda.getTimestamp());
		this.setInvestidor(new InvestidorRest(acaoDemanda.getInvestidor()));
		this.setAcao(new AcaoRest(acaoDemanda.getAcao()));
	}
	public AcaoDemandaRest() {
	}
	
	private String id;
	private int quantia;
	private int quantiaComprada;
	private float preco;
	private String timestamp;
	private AcaoRest acao;
	private InvestidorRest investidor;
	
	

}
