package com.javaee.fabiola.acoes.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaee.fabiola.acoes.domain.Investidor;
import com.javaee.fabiola.acoes.domain.Mercado;
import com.javaee.fabiola.acoes.domain.Acao;
import com.javaee.fabiola.acoes.domain.AcaoDemanda;
import com.javaee.fabiola.acoes.domain.AcaoMercado;
import com.javaee.fabiola.acoes.domain.OfertaAcao;
import com.javaee.fabiola.acoes.emailsender.EmailSender;
import com.javaee.fabiola.acoes.repositories.OfertaRepository;

@Service
public class OfertaServiceImpl implements OfertaService {
	
	@Autowired
	OfertaRepository ofertaRepository;
	@Autowired
	InvestidorService investidorService;
	@Autowired
	DemandaService demandaaService;
	@Autowired
	AcaoService acaoService;
	@Autowired
	MercadoService mercadoService;

	@Override
	public OfertaAcao createNew(AcaoMercado acaoMercado) {
		EmailSender emailSender = new EmailSender();
		Investidor investidor = null;
		if (!acaoMercado.isEmpresaOferta()) {
			investidor = investidorService.getById(acaoMercado.getInvestidorId());
		}
		Acao acao = AcaoService.getById(acaoMercado.getAcaoId());

		OfertaAcao OfertaAcao = new OfertaAcao();
		OfertaAcao.setQuantia(acaoMercado.getQuantia());
		OfertaAcao.setQuantiaVendida(0);
		OfertaAcao.setPreco(acaoMercado.getPreco());
		if (acaoMercado.isEmpresaOferta()) {
			OfertaAcao.setEmpresaOferta(true);
		} else {
			OfertaAcao.setInvestidor(investidor);
		}
		OfertaAcao.setAcao(acao);
	Set<AcaoDemanda> acaoDemandas = acao.getDemandas();
		acaoDemandas.stream().forEach((acaoDemanda) -> {
			int quant = 0;
			if (acaoDemanda.getPreco() == OfertaAcao.getPreco() && acaoDemanda.getQuantia() - acaoDemanda.getQuantiaComprada() > 0) {
				if (OfertaAcao.getQuantia() - OfertaAcao.getQuantiaVendida() > acaoDemanda.getQuantia() - acaoDemanda.getQuantiaComprada()) {
					quant = acaoDemanda.getQuantia() - acaoDemanda.getQuantiaComprada();
				} else {
					quant = OfertaAcao.getQuantia() - OfertaAcao.getQuantiaVendida();
				}
				if (quant > 0) {
					acaoDemanda.setQuantiaComprada(acaoDemanda.getQuantiaComprada() + quant);
					OfertaAcao.setQuantiaVendida(OfertaAcao.getQuantiaVendida() + quant);
					demandaaService.save(acaoDemanda);
					
					Mercado mercado = new Mercado();
					mercado.setQuantia(quant);
					mercado.setPreco(acaoDemanda.getPreco());
					mercado.setOferta(OfertaAcao);
					mercado.setDemanda(acaoDemanda);
					mercadoService.save(mercado);
					
					if (OfertaAcao.isEmpresaOferta()) {
						acao.setQuantiaEmpresa(acao.getQuantiaEmpresa() - quant);
					}

					if (OfertaAcao.isEmpresaOferta()) {
						emailSender.SendEmail(OfertaAcao.getAcao().getEmpresa().getEmail(), 
								"Notificação de venda ação " + OfertaAcao.getAcao().getId(), 
								Integer.toString(quant) + " ações foram vendidas com sucesso no valor de " + acaoDemanda.getPreco() + " (preço unitário)."
						);
					} else {
						emailSender.SendEmail(OfertaAcao.getInvestidor().getEmail(), 
								"Notificação de venda ação " + OfertaAcao.getAcao().getId(), 
								Integer.toString(quant) + " ações foram vendidas com sucesso no valor de " + acaoDemanda.getPreco() + " (preço unitário)."
						);
					}
					emailSender.SendEmail(acaoDemanda.getInvestidor().getEmail(), 
							"Notificação de compra ação " + OfertaAcao.getAcao().getId(), 
							Integer.toString(quant) + " ações foram compradas com sucesso no valor de " + acaoDemanda.getPreco() + " (preço unitário)."
					);
				}
			}
		});
		ofertaRepository.save(OfertaAcao);
		
		Set<OfertaAcao> sOfertas = acao.getOfertas();
		sOfertas.add(OfertaAcao);
		acao.setOferta(sOfertas);
		acaoService.save(acao.getId(), acao);

		if (!acaoMercado.isEmpresaOferta()) {
			Set<OfertaAcao> iDemandas = investidor.getOfertas();
			iDemandas.add(OfertaAcao);
			investidor.setOfertas(iDemandas);
			investidorService.save(investidor.getId(), investidor);
		}

		return OfertaAcao;
	}

	@Override
	public OfertaAcao save(OfertaAcao oferta) {
		oferta.setId(oferta.getId());
		return ofertaRepository.save(oferta);
	}

}
