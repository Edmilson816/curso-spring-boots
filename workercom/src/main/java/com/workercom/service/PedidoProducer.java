package com.workercom.service;

import java.math.BigDecimal;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workercom.model.Cartao;
import com.workercom.model.Pedido;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class PedidoProducer {

	private final RabbitTemplate rabbitTemplate;
	private final Queue queue;
	private final ObjectMapper mapper;
	private final CartaoService cartaoService;
	
	@SneakyThrows
	@PostMapping
	public void enviarPedido(Pedido pedido) {
		pedido.setCartao(Cartao.builder()
				.numero(cartaoService.gerarCartao())
				.limiteDisponivel(cartaoService.gerarLimite()).build());
		rabbitTemplate.convertAndSend(queue.getName(), mapper.writeValueAsString(pedido));
		log.info("Pedido montado com sucesso em worker compras PedidoProducer");
	}
	
	
}
