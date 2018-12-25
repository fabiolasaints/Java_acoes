package com.javaee.fabiola.acoes.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaee.fabiola.acoes.config.RabbitMQConfig;
import com.javaee.fabiola.acoes.domain.Mensagem;
import com.javaee.fabiola.acoes.services.DemandaService;
import com.javaee.fabiola.acoes.services.OfertaService;

@Component
public class MensagemListener {

	static final Logger logger = LoggerFactory.getLogger(MensagemListener.class);

	@Autowired
	private OfertaService ofertaService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_MESSAGES)
    public void processMensagem(Mensagem mensagem) {
    		switch (mensagem.getFonte()) {
			case "demanda":
				DemandaService.createNew(mensagem.getData());
				break;

			case "oferta":
				ofertaService.createNew(mensagem.getData());
				break;

			default:
				logger.info("Fonte Invalida...");
				break;
		}
    }
	
}
