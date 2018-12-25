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
@Document(collection="acoes-demandas")
public class AcaoDemanda {
	
	@Id
	private String id = UUID.randomUUID().toString();
	private int quantia;
	private int quantiaComprada;
	private float preco;
	private String timestamp = new Timestamp(System.currentTimeMillis()).toString();

	@DBRef(lazy = true)
	private Acao acao;
	@DBRef(lazy = true)
	private Investidor investidor;
	@DBRef(lazy = true)
	private Set<Mercado> mercados = new HashSet<>();;

}
