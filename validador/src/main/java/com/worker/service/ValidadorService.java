package com.worker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worker.exceptions.LimiteIndisponivelException;
import com.worker.exceptions.SaldoInsuficienteException;
import com.worker.model.Cartao;
import com.worker.model.Pedido;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ValidadorService {
	
	@Autowired
	private EmailService emailService;

	public void validarPedido(Pedido pedido) {
		validarLimiteDisponivel(pedido);
		validarCompraComLimite(pedido);
		emailService.notificarClienteCompra(pedido, "Compra confirmada!", "sua compra foi aprovada, por favor, aguarde o código de rastreio!");
	}
	
	private void validarLimiteDisponivel(Pedido pedido){
		if (pedido.getCartao().getLimiteDisponivel().longValue() <= 0) {
        	emailService.notificarClienteCompra(pedido, "Compra não autorizada!", "infelizmente sua compra não foi aprovada, por limite suficiente!");
			throw new LimiteIndisponivelException("Limite Indisponivel!");
		}
	}
	
	public void validarCompraComLimite(Pedido pedido) {
	  if (pedido.getValor().longValue()  > pedido.getCartao().getLimiteDisponivel().longValue() ) {
		  emailService.notificarClienteCompra(pedido, "Compra não autorizada!", "infelizmente sua compra não foi aprovada, por limite suficiente!");
		  log.error("Valor do Pedido: {}, Limite Disponivel: {}", pedido.getValor(), pedido.getCartao().getLimiteDisponivel());
		  throw new SaldoInsuficienteException("Você não possui limite suficiente para realizar esta compra!");
	  }	
	}
	
}
