package com.javaee.fabiola.acoes.domain;

import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection="mercados")
public class Mercado {

	@Id
	private String id = UUID.randomUUID().toString();
	private float preco;
	private int quantia;
	private String timestamp = new Timestamp(System.currentTimeMillis()).toString();
		
	
	@DBRef(lazy = true)
	private OfertaAcao oferta;
	@DBRef(lazy = true)
	private AcaoDemanda demanda;

}