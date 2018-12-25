package com.javaee.fabiola.acoes.domain;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection="mensagens")
public class Mensagem implements Serializable{
	public Mensagem(String fonte, AcaoMercado acaoMercado) {
		this.setFonte(fonte);
		this.setData(acaoMercado);
	}
	private void setFonte(String fonte2) {
		
		
	}
	public Mensagem() {		
	}
	
	private static final long serialVersionUID = 1L;
	
	private String id = UUID.randomUUID().toString();
	private String fonte; 
	private AcaoMercado data;


}