package com.workercom.service;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workercom.model.Pedido;
import com.workercom.service.EmailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class Consumer { /*Este objeto é responsavel por ler as informações no rabbitMQ*/

    private final EmailService emailService;
	private final ObjectMapper mapper;
	
	//@RabbitListener(queues = {"${queue.name}", "${queue.name}", "${queue.name}"}) Eu posso monitorar varias filas conforme exemplo
	/*@RabbitListener(queues = {"${queue.name}"})
	public void consumer(@Payload Message message) {
        Pedido pedido = mapper.readValue(message.getBody(), Pedido.class);
		System.out.println("Menssagem recebida: "+pedido);
		
	}*/
	
	
	@RabbitListener(queues = {"${queue.name}"})
    public void consumer(@Payload Message message) throws IOException {
        var pedido = mapper.readValue(message.getBody(), Pedido.class);
        log.info("Pedido Recebido: {}", pedido);
        emailService.notificarCliente(pedido);
    }	

}
