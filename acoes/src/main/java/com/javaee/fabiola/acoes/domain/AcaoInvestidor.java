package com.javaee.fabiola.acoes.domain;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection="acao-investidores")
public class AcaoInvestidor {
	
	@Id
	private String id = UUID.randomUUID().toString();
	
	private int quantia;
	private String InvestidorId;
	private String AcaoId;
	
	
}
