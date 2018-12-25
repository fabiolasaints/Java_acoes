package com.javaee.fabiola.acoes.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection="acoes")
public class Acao {
	@Id
	private String id = UUID.randomUUID().toString();
	private int quantia;
	private String nome;
	private float precoInicial;
	private int quantiaEmpresa;
	private String timestamp = new Timestamp(System.currentTimeMillis()).toString();
	
	@DBRef(lazy = true)
	private Set<AcaoMercado> mercado = new HashSet<>();
	
	@DBRef(lazy = true)
	private Empresa empresa;
	
	@DBRef(lazy = true)
	private Set<AcaoDemanda> demandas = new HashSet<>();

	@DBRef(lazy = true)
	private Set<AcaoInvestidor> donos = new HashSet<>();

	

	@DBRef(lazy = true)
	private Set<OfertaAcao> oferta = new HashSet<>();



	public Set<OfertaAcao> getOfertas() {
		
		return null;
	}
}