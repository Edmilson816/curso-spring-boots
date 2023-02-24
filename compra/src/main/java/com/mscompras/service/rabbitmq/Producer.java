package com.mscompras.service.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mscompras.model.Pedido;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class Producer {
	
	private final RabbitTemplate rabitTemplate;
	
	private final Queue queue;
	
	private final ObjectMapper mapper;
	
	@SneakyThrows /*Tratamento de excessão*/
	@PostMapping
	public void enviarPedido(Pedido pedido) {         /*o mapper abaixo serve para enviar o objeto no formato json*/      
		rabitTemplate.convertAndSend(queue.getName(), mapper.writeValueAsString(pedido)); /*Envia para o rabbit servico de menssageria*/
	}
	
	

}
