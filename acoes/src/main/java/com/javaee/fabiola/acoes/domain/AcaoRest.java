package com.javaee.fabiola.acoes.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcaoRest {
	public AcaoRest(Acao acao) {
		this.setId(acao.getId());
		this.setPrecoInicial(acao.getPrecoInicial());
		this.setQuantia(acao.getQuantia());
		this.setNome(acao.getNome());
		this.setQuantiaEmpresa(acao.getQuantiaEmpresa());
		this.setTimestamp(acao.getTimestamp());
	}
	private String id;
	private float precoInicial;
	private int quantia;
	private String nome;
	private int quantiaEmpresa;
	private String timestamp;

}