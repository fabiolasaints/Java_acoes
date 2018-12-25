package com.javaee.fabiola.acoes.services;



import java.util.Set;
//import java.util.TreeSet;
//import java.util.function.Predicate;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaee.fabiola.acoes.domain.Acao;
import com.javaee.fabiola.acoes.domain.OfertaAcao;
import com.javaee.fabiola.acoes.domain.AcaoDemanda;
import com.javaee.fabiola.acoes.domain.AcaoMercado;
import com.javaee.fabiola.acoes.domain.Investidor;
import com.javaee.fabiola.acoes.domain.Mercado;
import com.javaee.fabiola.acoes.emailsender.EmailSender;
import com.javaee.fabiola.acoes.repositories.DemandaRepository;


@Service
public class DemandaServiceImpl implements DemandaService {
	
	InvestidorService investidorService;
	
	DemandaRepository demandaRepository;
	
	MercadoService mercadoService;
	
	OfertaService ofertaService;
	
	AcaoService acaoService;
	
	

	@Override
	public AcaoDemanda createNew1(AcaoMercado acaoMercado) {
		EmailSender emailSender = new EmailSender();
		Investidor investidor = investidorService.getById(acaoMercado.getInvestidorId());
		Acao acao = AcaoService.getById(acaoMercado.getAcaoId());

		AcaoDemanda acaoDemanda = new AcaoDemanda();
		acaoDemanda.setQuantia(acaoMercado.getQuantia());
		acaoDemanda.setPreco(acaoMercado.getPreco());
		acaoDemanda.setQuantiaComprada(0);
		acaoDemanda.setInvestidor(investidor);
		acaoDemanda.setAcao(acao);

		Set<OfertaAcao> acaoOfertas = acao.getOfertas();
		acaoOfertas.stream().forEach((acaoOferta) -> {
			int quant = 0;
			if (acaoOferta.getPreco() == acaoDemanda.getPreco() && acaoOferta.getQuantia() - acaoOferta.getQuantiaVendida() > 0) {
				if (acaoOferta.getQuantia() - acaoOferta.getQuantiaVendida() > acaoDemanda.getQuantia() - acaoDemanda.getQuantiaComprada()) {
					quant = acaoDemanda.getQuantia() - acaoDemanda.getQuantiaComprada();
				} else {
					quant = acaoOferta.getQuantia() - acaoOferta.getQuantiaVendida();
				}

				if (quant > 0) {
					acaoDemanda.setQuantiaComprada(acaoDemanda.getQuantiaComprada() + quant);
					acaoOferta.setQuantiaVendida(acaoOferta.getQuantiaVendida() + quant);
					ofertaService.save(acaoOferta);
					
					acaoMercado.setDemanda(acaoDemanda);
					acaoMercado.setQuantia(quant);
					acaoMercado.setPreco(acaoDemanda.getPreco());
					Mercado mercado = new Mercado();
					mercado.setOferta(acaoOferta);
					mercadoService.save(mercado);
					
					if (acaoOferta.isEmpresaOferta()) {
						acao.setQuantiaEmpresa(acao.getQuantiaEmpresa() - quant);
					}
					
					if (acaoOferta.isEmpresaOferta()) {
						emailSender.SendEmail(acaoOferta.getAcao().getEmpresa().getEmail(), 
								"Aviso ação vendida " + acaoOferta.getAcao().getId(), 
								Integer.toString(quant) + " ações vendidas no valor de " + acaoDemanda.getPreco() + " (cada)."
						);
					} else {
						emailSender.SendEmail(acaoOferta.getInvestidor().getEmail(), 
								"Aviso venda de ação " + acaoOferta.getAcao().getId(), 
								Integer.toString(quant) + " ações vendidas no valor de " + acaoDemanda.getPreco() + " (cada)."
						);
					}
					emailSender.SendEmail(acaoDemanda.getInvestidor().getEmail(), 
							"Aviso compra ação " + acaoOferta.getAcao().getId(), 
							Integer.toString(quant) + " ações compradas no valor de " + acaoDemanda.getPreco() + " (cada)."
					);

				}
			}
		});
		demandaRepository.save(acaoDemanda);

		Set<AcaoDemanda> iDemandas = investidor.getDemandas();
		iDemandas.add(acaoDemanda);
		investidor.setDemandas(iDemandas);
		investidorService.save(investidor.getId(), investidor);
		
		Set<AcaoDemanda> sDemandas = acao.getDemandas();
		sDemandas.add(acaoDemanda);
		acao.setDemandas(sDemandas);
		acaoService.save(acao.getId(), acao);
		
		return acaoDemanda;
	}

	public Set<AcaoDemanda> getAll() {
		
		return null;
	}

	@Override
	public Set<AcaoDemanda> getAllByAcao(Acao acao) {
		
		return null;
	}
	@Override
	public AcaoDemanda save(AcaoDemanda demanda) {
	
		return null;
	}

	

}
