package com.javaee.fabiola.acoes.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdMensagem {
	public IdMensagem(String id) {
		this.setId(id);
	}

	private String id;

}
