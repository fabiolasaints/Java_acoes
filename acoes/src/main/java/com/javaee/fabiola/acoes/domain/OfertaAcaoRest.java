package com.javaee.fabiola.acoes.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfertaAcaoRest { // Consumer
	
	public OfertaAcaoRest(OfertaAcao acaoOferta) {
		this.setId(acaoOferta.getId());
		this.setPreco(acaoOferta.getPreco());
		this.setQuantia(acaoOferta.getQuantia());
		this.setQuantiaVendida(acaoOferta.getQuantiaVendida());
		this.setTimestamp(acaoOferta.getTimestamp());
		if (acaoOferta.isEmpresaOferta()) {
			this.setEmpresaOferta(true);
		} else {
			this.setEmpresaOferta(false);
			this.setInvestidor(new InvestidorRest(acaoOferta.getInvestidor()));
		}
		this.setAcao(new AcaoRest(acaoOferta.getAcao()));
	}
	public OfertaAcaoRest() {
	}
	
	private String id;
	private boolean empresaOferta;
	private float preco;
	private int quantia;
	private int quantiaVendida;
	private String timestamp;
    private InvestidorRest investidor;
	private AcaoRest acao;
	

}
