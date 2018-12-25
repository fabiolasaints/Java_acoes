package com.javaee.fabiola.acoes.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaee.fabiola.acoes.domain.Empresa;
import com.javaee.fabiola.acoes.domain.Mensagem;
import com.javaee.fabiola.acoes.domain.IdMensagem;
import com.javaee.fabiola.acoes.domain.Acao;
import com.javaee.fabiola.acoes.domain.AcaoDemanda;
import com.javaee.fabiola.acoes.domain.AcaoMercado;
import com.javaee.fabiola.acoes.domain.OfertaAcao;
import com.javaee.fabiola.acoes.repositories.AcaoRepository;
import com.javaee.fabiola.acoes.config.RabbitMQConfig;

@Service
public abstract class AcaoServiceImpl implements AcaoService {

	@Autowired
	private AcaoRepository AcaoRepository;
	@Autowired
	private EmpresaService EmpresaService;
	@Autowired
	private MensagemService MensagemService;
	@Autowired
	private DemandaService DemandaService;
	@Autowired
	private OfertaService OfertaService;
	
	private final RabbitTemplate rabbitTemplate;

	public AcaoServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
	}
	
	@Override
	public IdMensagem sendMensagem(String fonte, AcaoMercado acaoMercado) {
		Mensagem Mensagem = MensagemService.createNew(new Mensagem(fonte, acaoMercado));
		this.rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_MensagemS, Mensagem);
		return new IdMensagem(Mensagem.getId());
	}

	@Override
	public Set<Acao> getAll() {
		Set<Acao> Acoes = new HashSet<>();
		AcaoRepository.findAll().iterator().forEachRemaining(Acoes::add);
		return Acoes;
	}

	public Acao getById(String id) {
		return getAcaoById(id);
	}

	private Acao getAcaoById(String id) {
		Optional<Acao> AcaoOptional = AcaoRepository.findById(id);

		if (!AcaoOptional.isPresent()) {
			throw new IllegalArgumentException("Acao não econtrada ID value: " + id.toString());
		}

		return AcaoOptional.get();
	}
	
	@Override
	public Acao createNew(Acao Acao) {
		Acao AcaoInd0;
		try {
			AcaoInd0 = AcaoRepository.findByName(Acao.getNome()).get(0);
		} catch (Exception e) {
			return AcaoRepository.save(Acao);
		}
		throw new IllegalArgumentException("Acao já existe com ID: " + AcaoInd0.getId());
	}

	@Override
	public Acao emitNew(String EmpresaId, Acao Acao) {
		Empresa Empresa = EmpresaService.getById(EmpresaId);
		Acao.setEmpresa(Empresa);
		Acao.setQuantiaEmpresa(Acao.getQuantia());
		Set<Acao> Acaos = Empresa.getAcoes();
		Acaos.add(Acao);
		Empresa.setAcoes(Acaos);
		this.createNew(Acao);
		EmpresaService.save(EmpresaId, Empresa);

		AcaoMercado AcaoMercado = new AcaoMercado();
		AcaoMercado.setAcaoId(Acao.getId());
		AcaoMercado.setQuantia(Acao.getQuantia());
		AcaoMercado.setPreco(Acao.getPrecoInicial());
		AcaoMercado.setEmpresaOferta(true);
		OfertaService.createNew(AcaoMercado);

		return Acao;
	}

	@Override
	public Acao save(String id, Acao Acao) {
		Acao.setId(id);
		return AcaoRepository.save(Acao);
	}

	@Override
	public void deleteById(String id) {
		AcaoRepository.deleteById(id);
	}

	public Set<AcaoDemanda> getAllDemandas() {
		return DemandaService.getAll();
		
	}

	@Override
	public Set<OfertaAcao> getAllOffers() {
		
		return null;
	}



}
