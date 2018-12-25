package com.javaee.fabiola.acoes.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvestidorRest {
	public InvestidorRest(Investidor investidor) {
		this.setId(investidor.getId());
		this.setNome(investidor.getNome());
		this.setEmail(investidor.getEmail());
		this.setTimestamp(investidor.getTimestamp());
	}
	
	private String id;
	private String nome;
	private String email;
	private String timestamp;

}
